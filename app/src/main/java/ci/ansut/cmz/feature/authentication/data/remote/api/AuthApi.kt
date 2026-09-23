package ci.ansut.cmz.feature.authentication.data.remote.api

import ci.ansut.cmz.feature.authentication.data.remote.dto.AuthResponseDto
import ci.ansut.cmz.feature.authentication.data.remote.dto.GoogleSignInRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthApi(
    private val httpClient: HttpClient,
) {

    suspend fun signInWithGoogle(
        idToken: String,
    ): AuthResponseDto {
        return httpClient
            .post("auth/google") {
                contentType(ContentType.Application.Json)
                setBody(
                    GoogleSignInRequestDto(
                        idToken = idToken,
                    ),
                )
            }
            .body()
    }

    suspend fun signOut() {
        httpClient.post("auth/logout")
    }
}