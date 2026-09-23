package ci.ansut.cmz.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import ci.ansut.cmz.feature.authentication.presentation.login.LoginRoute

@Composable
fun AppNavigation(
    isAuthenticated: Boolean,
) {
    val backStack = rememberNavBackStack(
        if (isAuthenticated) {
            AppRoute.Home
        } else {
            AppRoute.Login
        },
    )

    LaunchedEffect(isAuthenticated) {
        val destination =
            if (isAuthenticated) {
                AppRoute.Home
            } else {
                AppRoute.Login
            }

        if (backStack.lastOrNull() != destination) {
            backStack.clear()
            backStack.add(destination)
        }
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {

            entry<AppRoute.Login> {
                LoginRoute()
            }

            entry<AppRoute.Home> {
                Text(
                    text = "Home",
                )
            }
        },
    )
}