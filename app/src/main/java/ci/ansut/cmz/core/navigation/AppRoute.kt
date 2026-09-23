package ci.ansut.cmz.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AppRoute : NavKey {
    @Serializable
    data object Login : AppRoute

    @Serializable
    data object Home : AppRoute

}