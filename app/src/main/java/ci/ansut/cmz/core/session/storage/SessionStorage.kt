package ci.ansut.cmz.core.session.storage

import ci.ansut.cmz.core.session.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionStorage {

    val session: Flow<Session?>

    suspend fun getSession(): Session?

    suspend fun saveSession(session: Session?)

    suspend fun clearSession()
}