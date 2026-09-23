package ci.ansut.cmz.feature.authentication.data.repository

import ci.ansut.cmz.core.session.SessionManager
import ci.ansut.cmz.core.session.model.Session
import ci.ansut.cmz.feature.authentication.data.mapper.AuthMapper
import ci.ansut.cmz.feature.authentication.data.remote.api.AuthApi
import ci.ansut.cmz.feature.authentication.domain.model.User
import ci.ansut.cmz.feature.authentication.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager,
) : AuthRepository {

    override suspend fun signInWithGoogle(
        idToken: String,
    ): User {

        val response =
            authApi.signInWithGoogle(
                idToken = idToken,
            )

        sessionManager.saveSession(
            Session(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken,
                expiresAt = response.expiresAt,
            ),
        )

        return AuthMapper.toDomain(
            dto = response.user,
        )
    }

    override suspend fun signOut() {
        try {
            authApi.signOut()
        } finally {
            sessionManager.clearSession()
        }
    }
}