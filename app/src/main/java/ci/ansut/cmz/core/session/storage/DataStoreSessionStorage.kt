package ci.ansut.cmz.core.session.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import ci.ansut.cmz.core.security.crypto.SecureCipher
import ci.ansut.cmz.core.session.model.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreSessionStorage(
    private val dataStore: DataStore<Preferences>,
    private val secureCipher: SecureCipher,
) : SessionStorage {

    override val session: Flow<Session?> =
        dataStore.data.map { preferences ->
            preferences.toSession()
        }

    override suspend fun getSession(): Session? {
        return session.first()
    }

    override suspend fun saveSession(session: Session?) {
        val encryptedAccessToken =
            secureCipher.encrypt(session?.accessToken)

        val encryptedRefreshToken =
            session?.refreshToken?.let(secureCipher::encrypt)

        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = encryptedAccessToken

            if (encryptedRefreshToken != null) {
                preferences[REFRESH_TOKEN] = encryptedRefreshToken
            } else {
                preferences.remove(REFRESH_TOKEN)
            }

            if (session?.expiresAt != null) {
                preferences[EXPIRES_AT] = session.expiresAt
            } else {
                preferences.remove(EXPIRES_AT)
            }
        }
    }

    override suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN)
            preferences.remove(REFRESH_TOKEN)
            preferences.remove(EXPIRES_AT)
        }
    }

    private fun Preferences.toSession(): Session? {
        val encryptedAccessToken =
            this[ACCESS_TOKEN] ?: return null

        val accessToken =
            secureCipher.decrypt(encryptedAccessToken)

        val refreshToken =
            this[REFRESH_TOKEN]
                ?.let(secureCipher::decrypt)

        return Session(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiresAt = this[EXPIRES_AT],
        )
    }

    private companion object {

        val ACCESS_TOKEN =
            stringPreferencesKey("session_access_token")

        val REFRESH_TOKEN =
            stringPreferencesKey("session_refresh_token")

        val EXPIRES_AT =
            longPreferencesKey("session_expires_at")
    }
}