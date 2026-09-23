package ci.ansut.cmz.feature.authentication.data.session

import ci.ansut.cmz.core.session.model.Session
import ci.ansut.cmz.core.session.refresh.SessionRefresher
import ci.ansut.cmz.feature.authentication.data.remote.dto.RefreshTokenRequestDto
import ci.ansut.cmz.feature.authentication.data.remote.dto.RefreshTokenResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthSessionRefresher : SessionRefresher {

    override suspend fun refresh(
        client: HttpClient,
        refreshToken: String,
    ): Session {
        val response =
            client.post("auth/refresh") {
                contentType(ContentType.Application.Json)

                contentType(
                    ContentType.Application.Json,
                )

                setBody(
                    RefreshTokenRequestDto(
                        refreshToken = refreshToken,
                    ),
                )
            }.body<RefreshTokenResponseDto>()

        return Session(
            accessToken = response.accessToken,
            refreshToken =
                response.refreshToken ?: refreshToken,
            expiresAt = response.expiresAt,
        )
    }
}