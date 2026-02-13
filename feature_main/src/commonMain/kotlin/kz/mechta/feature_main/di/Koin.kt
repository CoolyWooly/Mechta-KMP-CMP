package kz.mechta.feature_main.di

import kz.mechta.feature_catalog.di.catalogKoinModule
import kz.mechta.feature_home.di.homeModule
import org.koin.dsl.module

val mainModules = module {
    includes(homeModule)
    includes(catalogKoinModule)
}