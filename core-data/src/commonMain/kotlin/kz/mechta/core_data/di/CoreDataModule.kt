package kz.mechta.core_data.di

import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import kz.mechta.core_data.data.api.CityApi
import kz.mechta.core_data.data.network.NetworkClient
import kz.mechta.core_data.data.repository.CityRepositoryImpl
import kz.mechta.core_data.domain.repository.PrefsRepository
import kz.mechta.core_data.data.repository.PrefsRepositoryImpl
import kz.mechta.core_data.data.utils.NetworkUtil
import org.koin.dsl.module
import kz.mechta.core_data.domain.repository.CityRepository
import kz.mechta.core_data.domain.use_case.GetCitiesUseCase

val coreNetworkModule = module {
    single<Settings> { Settings() }

    single<PrefsRepository> { PrefsRepositoryImpl(get()) }

    single<HttpClient> { NetworkClient(get()).httpClient }

    single<NetworkUtil> { NetworkUtil }

    single<CityApi> { CityApi(get()) }
}

val coreRepositoryModule = module {
    single<CityRepository> {
        CityRepositoryImpl(get(), get())
    }
}

val coreUseCaseModule = module {
    factory { GetCitiesUseCase(get()) }
}