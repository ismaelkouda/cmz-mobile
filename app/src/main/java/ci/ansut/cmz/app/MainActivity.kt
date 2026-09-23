package ci.ansut.cmz.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ci.ansut.cmz.app.bootstrap.AppUiState
import ci.ansut.cmz.app.bootstrap.AppViewModel
import ci.ansut.cmz.core.navigation.AppNavigation
import ci.ansut.cmz.ui.theme.CmzTheme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("on create")
        enableEdgeToEdge()
        setContent {
            CmzTheme {

                val viewModel =
                    koinViewModel<AppViewModel>()

                val state by
                viewModel.uiState
                    .collectAsStateWithLifecycle()

                when (state) {

                    AppUiState.Loading -> {
                        // Splash / loading
                    }

                    AppUiState.Authenticated,
                    AppUiState.Unauthenticated -> {
                        AppNavigation(
                            isAuthenticated =
                                state is AppUiState.Authenticated,
                        )
                    }
                }
            }
        }
    }

}