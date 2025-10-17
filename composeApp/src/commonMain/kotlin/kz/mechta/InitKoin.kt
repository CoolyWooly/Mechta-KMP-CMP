package kz.mechta

import kz.mechta.core_data.di.coreNetworkModule
import kz.mechta.core_data.di.coreRepositoryModule
import kz.mechta.core_data.di.coreUseCaseModule
import kz.mechta.feature_onboarding.di.onboardingModule
import kz.mechta.feature_splashscreen.di.splashscreenModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import kotlin.jvm.JvmName

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            listOf(
                coreNetworkModule,
                coreRepositoryModule,
                coreUseCaseModule,
                splashscreenModule,
                onboardingModule,
            )
        )
    }
}

fun initKoinIos() = initKoin {}