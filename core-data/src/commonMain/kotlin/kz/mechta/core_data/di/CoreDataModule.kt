package kz.mechta.core_data.di

import io.ktor.client.*
import kz.mechta.core_data.data.api.CityApi
import kz.mechta.core_data.data.db.AppDatabase
import kz.mechta.core_data.data.network.NetworkClient
import kz.mechta.core_data.data.repository.AuthRepositoryImpl
import kz.mechta.core_data.data.repository.CartRepositoryImpl
import kz.mechta.core_data.data.repository.CityRepositoryImpl
import kz.mechta.core_data.data.utils.NetworkUtil
import kz.mechta.core_data.domain.repository.AuthRepository
import kz.mechta.core_data.domain.repository.CartRepository
import kz.mechta.core_data.domain.repository.CityRepository
import kz.mechta.core_data.domain.use_case.AddCartUseCase
import kz.mechta.core_data.domain.use_case.GetCartItemUseCase
import kz.mechta.core_data.domain.use_case.GetCitiesUseCase
import kz.mechta.core_data.domain.use_case.TrackCartUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val coreNetworkModule = module {
    single<HttpClient> { NetworkClient(get()).httpClient }
    single<NetworkUtil> { NetworkUtil }
    single<CityApi> { CityApi(get()) }
}

val coreDBModule = module {
    includes(databaseDriverModule)
    single<AppDatabase> { AppDatabase(get()) }
}

val coreRepositoryModule = module {
    single<CityRepository> { CityRepositoryImpl(get(), get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get(), get()) }
}

val coreUseCaseModule = module {
    // Cart
    factory { GetCartItemUseCase(get()) }
    factory { AddCartUseCase(get()) }
    factory { TrackCartUseCase(get()) }
    // City
    factory { GetCitiesUseCase(get()) }
}

expect val prefsModule: Module
expect val databaseDriverModule: Module