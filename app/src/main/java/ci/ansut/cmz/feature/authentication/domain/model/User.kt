package ci.ansut.cmz.feature.authentication.domain.model

data class User(
    val id: String,
    val email: String,
    val displayName: String?,
    val photoUrl: String? = null
)
