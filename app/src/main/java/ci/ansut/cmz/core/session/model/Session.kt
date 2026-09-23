package ci.ansut.cmz.core.session.model

data class Session(
    val accessToken: String,
    val refreshToken: String?,
    val expiresAt: Long?,
)