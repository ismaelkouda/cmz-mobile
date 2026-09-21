package ci.ansut.cmz.feature.authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ci.ansut.cmz.feature.authentication.platform.credential.GoogleCredentialManager
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = koinViewModel(),
    googleCredentialManager: GoogleCredentialManager = koinInject(),
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = { action ->
            when (action) {

                LoginAction.SignInWithGoogle -> {
                    coroutineScope.launch {
                        try {
                            val credential =
                                googleCredentialManager.signIn(
                                    context = context,
                                )

                            viewModel.signInWithGoogle(
                                idToken = credential.idToken,
                            )
                        } catch (exception: Exception) {
                            viewModel.onGoogleSignInError(
                                throwable = exception,
                            )
                        }
                    }
                }

                LoginAction.DismissError -> {
                    viewModel.dismissError()
                }
            }
        },
    )
}