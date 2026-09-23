package ci.ansut.cmz.feature.authentication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseDto(
    val accessToken: String,
    val refreshToken: String? = null,
    val expiresAt: Long? = null,
)