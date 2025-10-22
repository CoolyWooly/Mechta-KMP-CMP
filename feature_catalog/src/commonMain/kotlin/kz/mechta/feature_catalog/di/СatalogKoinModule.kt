package kz.mechta.feature_catalog.di

import kz.mechta.feature_catalog.data.network.CatalogApi
import kz.mechta.feature_catalog.data.repository.CatalogRepositoryImpl
import kz.mechta.feature_catalog.domain.repository.CatalogRepository
import kz.mechta.feature_catalog.domain.use_case.GetBrandsUseCase
import kz.mechta.feature_catalog.domain.use_case.GetCatalogUseCase
import kz.mechta.feature_catalog.presentation.CatalogViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val catalogKoinModule = module {

    single { CatalogApi(client = get()) }
    single<CatalogRepository> { CatalogRepositoryImpl(api = get(), networkUtil = get()) }
    viewModelOf(::CatalogViewModel)
    factory { GetCatalogUseCase(catalogRepository = get()) }
    factory { GetBrandsUseCase(catalogRepository = get()) }
}