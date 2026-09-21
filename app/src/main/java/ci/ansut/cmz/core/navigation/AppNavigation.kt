package ci.ansut.cmz.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import ci.ansut.cmz.feature.authentication.presentation.login.LoginRoute

@Composable
fun AppNavigation() {

    val backStack = rememberNavBackStack(
        AppRoute.Login,
    )

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {

            entry<AppRoute.Login> {
                LoginRoute()
            }
        },
    )
}