package kz.mechta.core_data.di

import io.ktor.client.*
import kz.mechta.core_data.data.api.CityApi
import kz.mechta.core_data.data.network.NetworkClient
import kz.mechta.core_data.data.repository.AuthRepositoryImpl
import kz.mechta.core_data.data.repository.CityRepositoryImpl
import kz.mechta.core_data.data.utils.NetworkUtil
import kz.mechta.core_data.domain.repository.AuthRepository
import kz.mechta.core_data.domain.repository.CityRepository
import kz.mechta.core_data.domain.use_case.GetCitiesUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val coreNetworkModule = module {

    single<HttpClient> { NetworkClient(get()).httpClient }

    single<NetworkUtil> { NetworkUtil }

    single<CityApi> { CityApi(get()) }
}

val coreRepositoryModule = module {

    single<CityRepository> { CityRepositoryImpl(get(), get()) }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

}

val coreUseCaseModule = module {
    factory { GetCitiesUseCase(get()) }
}

expect val prefsModule: Module