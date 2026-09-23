package ci.ansut.cmz.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import ci.ansut.cmz.core.security.crypto.SecureCipher
import ci.ansut.cmz.core.session.SessionManager
import ci.ansut.cmz.core.session.storage.DataStoreSessionStorage
import ci.ansut.cmz.core.session.storage.SessionStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = {
                androidContext()
                    .preferencesDataStoreFile(
                        "cmz_preferences",
                    )
            },
        )
    }

    single {
        SecureCipher()
    }

    single<SessionStorage> {
        DataStoreSessionStorage(
            dataStore = get(),
            secureCipher = get(),
        )
    }

    single {
        SessionManager(
            sessionStorage = get(),
        )
    }
}