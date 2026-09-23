package ci.ansut.cmz.core.network

import ci.ansut.cmz.core.session.refresh.SessionRefresher
import ci.ansut.cmz.core.session.storage.SessionStorage
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientFactory(
    private val apiConfig: ApiConfig,
    private val sessionStorage: SessionStorage,
    private val sessionRefresher: SessionRefresher,
) {

    fun create(): HttpClient {
        return HttpClient(CIO) {

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                        encodeDefaults = true
                    },
                )
            }

            install(DefaultRequest) {
                url(apiConfig.baseUrl)

                contentType(
                    ContentType.Application.Json,
                )
            }

            install(Auth) {
                bearer {
                    cacheTokens = false

                    loadTokens {
                        val session =
                            sessionStorage.getSession()
                                ?: return@loadTokens null

                        BearerTokens(
                            accessToken = session.accessToken,
                            refreshToken = session.refreshToken.orEmpty(),
                        )
                    }

                    refreshTokens {
                        val refreshToken =
                            oldTokens
                                ?.refreshToken
                                ?.takeIf(String::isNotBlank)
                                ?: return@refreshTokens null

                        val newSession =
                            try {
                                sessionRefresher.refresh(
                                    client = client,
                                    refreshToken = refreshToken,
                                )
                            } catch (_: Exception) {
                                sessionStorage.clearSession()

                                return@refreshTokens null
                            }

                        sessionStorage.saveSession(
                            session = newSession,
                        )

                        newSession?.accessToken?.let {
                            BearerTokens(
                                accessToken = it,
                                refreshToken =
                                    newSession.refreshToken.orEmpty(),
                            )
                        }
                    }
                }
            }

            install(Logging) {
                level = LogLevel.INFO
            }
        }
    }
}