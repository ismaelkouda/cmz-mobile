package ci.ansut.cmz.core.session.refresh

import ci.ansut.cmz.core.session.model.Session
import io.ktor.client.HttpClient

fun interface SessionRefresher {

    suspend fun refresh(
        client: HttpClient,
        refreshToken: String,
    ): Session?
}