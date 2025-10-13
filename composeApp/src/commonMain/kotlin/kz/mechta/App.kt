package kz.mechta

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.feature_onboarding.di.onboardingModule
import kz.mechta.feature_splashscreen.di.splashscreenModule
import kz.mechta.feature_splashscreen.presentation.SplashscreenScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.core.context.startKoin

@Composable
@Preview
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


    MechtaTheme {
        Navigator(
            screen = SplashscreenScreen(),
//            onBackPressed = { currentScreen ->
//                if (navigator.lastItem == currentScreen) {
//                    onFinishAppClick()
//                    true
//                } else {
//                    false
//                }
//            }
        )
    }
}