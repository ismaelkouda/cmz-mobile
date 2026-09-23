package ci.ansut.cmz.feature.authentication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    val displayName: String? = null,
    val photoUrl: String? = null,
)