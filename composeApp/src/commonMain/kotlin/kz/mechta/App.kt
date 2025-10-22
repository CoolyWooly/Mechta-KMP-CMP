package kz.mechta

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import kz.mechta.core_data.di.coreNetworkModule
import kz.mechta.core_data.di.coreRepositoryModule
import kz.mechta.core_data.di.coreUseCaseModule
import kz.mechta.core_data.di.prefsModule
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.feature_main.di.mainModules
import kz.mechta.feature_main.presentation.MainPage
import kz.mechta.feature_onboarding.di.onboardingModule
import kz.mechta.feature_onboarding.presentation.OnboardingPage
import kz.mechta.feature_splashscreen.di.splashscreenModule
import kz.mechta.feature_splashscreen.presentation.SplashscreenPage
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinAppDeclaration

@Serializable
private sealed class RootRoute() {

    @Serializable
    data object SplashscreenRoute: RootRoute()

    @Serializable
    data object OnboardingRoute: RootRoute()

    @Serializable
    data object MainRoute: RootRoute()
}


@Composable
fun App(
    koinAppDeclaration: KoinAppDeclaration? = null
) {
    KoinApplication(
        application = {
            koinAppDeclaration?.invoke(this)
            modules(
                coreNetworkModule,
                coreRepositoryModule,
                coreUseCaseModule,
                splashscreenModule,
                onboardingModule,
                prefsModule,
                mainModules,
            )
        }
    ) {
        val navController = rememberNavController()
        MechtaTheme {
            NavHost(
                modifier = Modifier.systemBarsPadding(),
                navController = navController,
                startDestination = RootRoute.SplashscreenRoute
            ) {
                composable<RootRoute.SplashscreenRoute> {
                    SplashscreenPage(
                        navigateToOnboarding = {
                            navController.popBackStack()
                            navController.navigate(RootRoute.OnboardingRoute)
                        },
                        navigateToMain = {
                            navController.popBackStack()
                            navController.navigate(RootRoute.MainRoute)
                        }
                    )
                }
                composable<RootRoute.OnboardingRoute> { backStackEntry ->
                    OnboardingPage(
                        navigateToMain = {
                            navController.popBackStack()
                            navController.navigate(RootRoute.MainRoute)
                        }
                    )
                }
                composable<RootRoute.MainRoute> { backStackEntry ->
                    MainPage(
//                    navigateToMain = {
//                        navController.navigate(MainRoute)
//                    }
                    )
                }
            }
        }
    }
}