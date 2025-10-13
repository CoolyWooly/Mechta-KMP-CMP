package kz.mechta.feature_onboarding.presentation

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kz.mechta.feature_main.presentation.MainPage
import org.koin.compose.viewmodel.koinViewModel

class OnboardingScreen() : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<OnboardingViewModel>()
        val state by viewModel.state.collectAsState()
        val lifecycleOwner = LocalLifecycleOwner.current

        // UI по состоянию
        when (state.currentPage) {
            0 -> {}
            1 -> {}
        }

        Scaffold (
            modifier = Modifier.safeContentPadding()
        ) {
            Button(onClick = { viewModel.handleEvent(Event.Finish) }) { Text("Дальше") }
        }

        // Эффекты
        LaunchedEffect(lifecycleOwner) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    Effect.NavigateToHome -> { navigator.push(MainPage()) }
                }
            }
        }
    }
}