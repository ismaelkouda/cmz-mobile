package ci.ansut.cmz.app.bootstrap

sealed interface AppUiState {

    data object Loading : AppUiState

    data object Authenticated : AppUiState

    data object Unauthenticated : AppUiState
}