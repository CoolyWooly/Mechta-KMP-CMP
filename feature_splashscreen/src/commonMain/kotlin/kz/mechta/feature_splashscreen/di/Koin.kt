package kz.mechta.feature_splashscreen.di

import kz.mechta.feature_splashscreen.presentation.SplashscreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val splashscreenModule = module {
     viewModel { SplashscreenViewModel() }
}