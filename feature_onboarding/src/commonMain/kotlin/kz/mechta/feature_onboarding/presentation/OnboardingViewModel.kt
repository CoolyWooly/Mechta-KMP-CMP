package kz.mechta.feature_onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class OnboardingViewModel : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect

    fun handleEvent(event: Event) {
        when (event) {
            is Event.NextPage -> { /* обновить _state и т.д. */ }
            is Event.Finish -> { viewModelScope.launch { _effect.emit(Effect.NavigateToHome) } }
            // другие ивенты
        }
    }
}