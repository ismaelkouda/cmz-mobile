package ci.ansut.cmz.di

import ci.ansut.cmz.feature.authentication.di.authenticationModule
import org.koin.dsl.module


val appModule = module {

    includes(
        networkModule,
        authenticationModule,
    )
}