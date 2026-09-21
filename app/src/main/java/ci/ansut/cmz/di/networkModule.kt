package ci.ansut.cmz.di

import ci.ansut.cmz.core.config.network.ApiConfig
import ci.ansut.cmz.core.config.network.HttpClientFactory
import ci.ansut.cmz.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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
        )
    }

    single<HttpClient> {
        get<HttpClientFactory>().create()
    }

}