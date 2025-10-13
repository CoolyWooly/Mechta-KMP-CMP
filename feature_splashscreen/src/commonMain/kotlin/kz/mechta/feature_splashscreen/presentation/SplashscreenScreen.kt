package kz.mechta.feature_splashscreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.LocalLifecycleOwner
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.feature_onboarding.presentation.OnboardingScreen
import org.koin.compose.viewmodel.koinViewModel

class SplashscreenScreen() : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<SplashscreenViewModel>()
        val state by viewModel.state.collectAsState()
        val lifecycleOwner = LocalLifecycleOwner.current

        Scaffold (
            modifier = Modifier.fillMaxSize(),
            containerColor = MechtaTheme.colors.brandBaseBrand
        ){
        }

        // Эффекты
        LaunchedEffect(lifecycleOwner) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    Effect.NavigateToMain -> { navigator.replace(OnboardingScreen()) }
                    Effect.NavigateToOnboarding -> { navigator.replace(OnboardingScreen()) }
                }
            }
        }
    }
}