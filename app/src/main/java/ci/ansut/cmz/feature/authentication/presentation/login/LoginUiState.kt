package ci.ansut.cmz.feature.authentication.presentation.login

import ci.ansut.cmz.feature.authentication.domain.model.User

data class LoginUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null,
)