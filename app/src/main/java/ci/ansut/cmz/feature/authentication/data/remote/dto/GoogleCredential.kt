package ci.ansut.cmz.feature.authentication.data.remote.dto

data class GoogleCredential(
    val idToken: String,
    val email: String,
    val displayName: String?,
    val profilePictureUrl: String?,
)