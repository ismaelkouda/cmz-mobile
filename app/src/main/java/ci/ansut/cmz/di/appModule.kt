package ci.ansut.cmz.di

import ci.ansut.cmz.app.bootstrap.AppViewModel
import ci.ansut.cmz.core.preferences.preferencesModule
import ci.ansut.cmz.feature.authentication.di.authenticationModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    includes(
        networkModule,
        preferencesModule,
        authenticationModule,
    )

    viewModel {
        AppViewModel(
            sessionManager = get(),
        )
    }
}