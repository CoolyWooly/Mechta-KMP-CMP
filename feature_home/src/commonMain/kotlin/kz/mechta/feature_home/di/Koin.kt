package kz.mechta.feature_home.di

import kz.mechta.feature_home.data.network.HomeApi
import kz.mechta.feature_home.data.repository.HomeRepositoryImpl
import kz.mechta.feature_home.domain.repository.HomeRepository
import kz.mechta.feature_home.domain.use_case.GetBannersUseCase
import kz.mechta.feature_home.domain.use_case.GetBrandsUseCase
import kz.mechta.feature_home.domain.use_case.GetCategoriesUseCase
import kz.mechta.feature_home.domain.use_case.GetNewsUseCase
import kz.mechta.feature_home.domain.use_case.GetPublicationsUseCase
import kz.mechta.feature_home.domain.use_case.GetSocialsUseCase
import kz.mechta.feature_home.domain.use_case.GetTopCategoriesUseCase
import kz.mechta.feature_home.domain.use_case.SubscribeEmailUseCase
import kz.mechta.feature_home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    // Network
    single<HomeApi> { HomeApi(get()) }

    // Repository
    single<HomeRepository> { HomeRepositoryImpl(get()) }

    // Use Cases
    factory { GetBannersUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { GetBrandsUseCase(get()) }
    factory { GetPublicationsUseCase(get()) }
    factory { GetNewsUseCase(get()) }
    factory { GetTopCategoriesUseCase(get()) }
    factory { GetSocialsUseCase(get()) }
    factory { SubscribeEmailUseCase(get()) }

    // ViewModel
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
}
