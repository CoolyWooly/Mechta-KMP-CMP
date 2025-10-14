package kz.mechta.feature_onboarding.presentation

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingPage(
    navigateToMain: () -> Unit,
) {

    val viewModel = koinViewModel<OnboardingViewModel>()
    val state by viewModel.state.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    // UI по состоянию
    when (state.currentPage) {
        0 -> {}
        1 -> {}
    }

    Scaffold(
        modifier = Modifier.safeContentPadding()
    ) {
        Button(onClick = { viewModel.handleEvent(Event.Finish) }) { Text("Дальше") }
    }

    // Эффекты
    LaunchedEffect(lifecycleOwner) {
        viewModel.effect.collect { effect ->
            when (effect) {
                Effect.NavigateToHome -> {
                    navigateToMain()
                }
            }
        }
    }
}