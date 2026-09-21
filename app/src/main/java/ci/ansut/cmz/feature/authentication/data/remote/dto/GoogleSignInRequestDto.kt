package ci.ansut.cmz.feature.authentication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleSignInRequestDto(
    @SerialName("id_token")
    val idToken: String,
)