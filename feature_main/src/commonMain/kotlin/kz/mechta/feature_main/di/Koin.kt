package kz.mechta.feature_main.di

import kz.mechta.feature_catalog.di.catalogKoinModule
import org.koin.dsl.module

val mainModules = module {
    includes(catalogKoinModule)
}