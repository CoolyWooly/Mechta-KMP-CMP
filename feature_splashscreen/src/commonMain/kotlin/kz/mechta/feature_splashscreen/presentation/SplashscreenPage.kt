package kz.mechta.feature_splashscreen.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.feature_onboarding.presentation.OnboardingPage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashscreenPage(
    navigateToOnboarding: () -> Unit,
    navigateToMain: () -> Unit,
) {
    val viewModel = koinViewModel<SplashscreenViewModel>()
    val state by viewModel.state.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MechtaTheme.colors.brandBaseBrand
    ) {
    }

    // Эффекты
    LaunchedEffect(lifecycleOwner) {
        viewModel.effect.collect { effect ->
            when (effect) {
                Effect.NavigateToMain -> {
                    navigateToMain()
                }

                Effect.NavigateToOnboarding -> {
                    navigateToOnboarding()
                }
            }
        }
    }
}