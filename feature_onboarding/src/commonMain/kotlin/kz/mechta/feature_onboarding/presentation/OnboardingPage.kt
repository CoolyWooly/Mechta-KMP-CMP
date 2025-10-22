package kz.mechta.feature_onboarding.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import kz.mechta.core_ui.components.CustomTopAppBar
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.mechta_bonuses
import kz.mechta.core_ui.utils.collectInLaunchedEffect
import kz.mechta.core_ui.utils.use
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingPage(
    navigateToMain: () -> Unit,
) {
    val viewModel = koinViewModel<OnboardingViewModel>()
    val (state, event, effect) = use(viewModel = viewModel)

    Scaffold(
        modifier = Modifier.safeContentPadding(),
        topBar = {
            CustomTopAppBar(
                text = stringResource(Res.string.mechta_bonuses),
            )
        }
    ) {
        Text(
            text = "Ваш город:"
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(state.cities) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { event(OnboardingContract.Event.OnCityClick(it)) }
                ) {
                    Text(it.name)
                }
            }
        }
    }

    effect.collectInLaunchedEffect {
        when (it) {
            is OnboardingContract.Effect.NavigateToMain -> navigateToMain()
        }
    }
}