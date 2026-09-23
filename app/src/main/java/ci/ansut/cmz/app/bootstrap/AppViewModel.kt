package ci.ansut.cmz.app.bootstrap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.ansut.cmz.core.session.SessionManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    sessionManager: SessionManager,
) : ViewModel() {

    val uiState: StateFlow<AppUiState> =
        sessionManager.session
            .map { session ->
                if (session != null) {
                    AppUiState.Authenticated
                } else {
                    AppUiState.Unauthenticated
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(
                    stopTimeoutMillis = 5_000,
                ),
                initialValue = AppUiState.Loading,
            )
}