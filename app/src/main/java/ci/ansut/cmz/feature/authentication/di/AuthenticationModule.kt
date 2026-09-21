package ci.ansut.cmz.feature.authentication.di

import ci.ansut.cmz.BuildConfig
import androidx.credentials.CredentialManager
import ci.ansut.cmz.feature.authentication.application.usecase.SignInWithGoogleUseCase
import ci.ansut.cmz.feature.authentication.data.remote.api.AuthApi
import ci.ansut.cmz.feature.authentication.data.repository.AuthRepositoryImpl
import ci.ansut.cmz.feature.authentication.domain.repository.AuthRepository
import ci.ansut.cmz.feature.authentication.platform.credential.GoogleCredentialManager
import ci.ansut.cmz.feature.authentication.presentation.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {

    // Android Credential Manager
    single {
        CredentialManager.create(
            context = androidContext(),
        )
    }

    single {
        GoogleCredentialManager(
            credentialManager = get(),
            serverClientId = BuildConfig.GOOGLE_SERVER_CLIENT_ID
        )
    }

    single {
        AuthApi(
            httpClient = get(),
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            authApi = get(),
        )
    }

    // Application
    factory {
        SignInWithGoogleUseCase(
            authRepository = get(),
        )
    }

    // Presentation
    viewModel {
        LoginViewModel(
            signInWithGoogleUseCase = get(),
        )
    }
}