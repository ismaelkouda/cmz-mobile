package ci.ansut.cmz.feature.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.ansut.cmz.feature.authentication.application.usecase.SignInWithGoogleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun signInWithGoogle(
        idToken: String,
    ) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null,
                )
            }

            try {
                val user = signInWithGoogleUseCase(
                    idToken = idToken,
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        user = user,
                    )
                }
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = exception.message
                            ?: "Une erreur est survenue.",
                    )
                }
            }
        }
    }

    fun onGoogleSignInError(
        throwable: Throwable,
    ) {
        _uiState.update {
            it.copy(
                isLoading = false,
                error = throwable.message
                    ?: "Impossible de se connecter avec Google.",
            )
        }
    }

    fun dismissError() {
        _uiState.update {
            it.copy(error = null)
        }
    }
}