package kz.mechta.feature_onboarding.di

import kz.mechta.feature_onboarding.presentation.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val onboardingModule = module {
     viewModel { OnboardingViewModel(get(), get()) }
}