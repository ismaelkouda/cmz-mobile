package ci.ansut.cmz.feature.authentication.presentation.login

sealed interface LoginAction {
    data object SignInWithGoogle : LoginAction
    data object DismissError : LoginAction
}