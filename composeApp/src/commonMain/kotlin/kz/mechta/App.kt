package kz.mechta

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.feature_onboarding.di.onboardingModule
import kz.mechta.feature_splashscreen.di.splashscreenModule
import kotlinx.serialization.Serializable
import kz.mechta.feature_main.presentation.MainPage
import kz.mechta.feature_onboarding.presentation.OnboardingPage
import kz.mechta.feature_splashscreen.presentation.SplashscreenPage
import org.koin.core.context.startKoin

@Serializable
data object SplashscreenRoute

@Serializable
data object OnboardingRoute

@Serializable
data object MainRoute

@Composable
fun App(
    onFinishAppClick: () -> Unit
) {
    startKoin {
        modules(
            listOf(
                splashscreenModule,
                onboardingModule,
            )
        )
    }

    val navController = rememberNavController()
    MechtaTheme {
        NavHost(
            navController = navController,
            startDestination = SplashscreenRoute
        ) {
            composable<SplashscreenRoute> {
                SplashscreenPage(
                    navigateToOnboarding = {
                        navController.navigate(OnboardingRoute)
                    },
                    navigateToMain = {
                        navController.navigate(MainRoute)
                    }
                )
            }
            composable<OnboardingRoute> { backStackEntry ->
                OnboardingPage(
                    navigateToMain = {
                        navController.navigate(MainRoute)
                    }
                )
            }
            composable<MainRoute> { backStackEntry ->
                MainPage(
//                    navigateToMain = {
//                        navController.navigate(MainRoute)
//                    }
                )
            }
        }
    }
}