package ci.ansut.cmz.core.session

import ci.ansut.cmz.core.session.model.Session
import ci.ansut.cmz.core.session.storage.SessionStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionManager(
    private val sessionStorage: SessionStorage,
) {

    val session: Flow<Session?> =
        sessionStorage.session

    val isAuthenticated: Flow<Boolean> =
        session.map { currentSession ->
            currentSession != null
        }

    suspend fun getSession(): Session? {
        return sessionStorage.getSession()
    }

    suspend fun saveSession(
        session: Session,
    ) {
        sessionStorage.saveSession(
            session = session,
        )
    }

    suspend fun clearSession() {
        sessionStorage.clearSession()
    }
}