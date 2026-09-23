package ci.ansut.cmz.di

import ci.ansut.cmz.core.network.ApiConfig
import ci.ansut.cmz.core.network.HttpClientFactory
import ci.ansut.cmz.BuildConfig
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {

    single {
        ApiConfig(
            baseUrl = BuildConfig.API_BASE_URL,
        )
    }

    single {
        HttpClientFactory(
            apiConfig = get(),
            sessionStorage = get(),
            sessionRefresher = get(),
        )
    }

    single<HttpClient> {
        get<HttpClientFactory>().create()
    }
}