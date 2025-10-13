package kz.mechta.feature_splashscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class SplashscreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect

    fun handleEvent(event: Event) {
    }

    init {
        viewModelScope.launch {
            delay(5000)
            if (true) {
                _effect.emit(Effect.NavigateToOnboarding)
            } else {
                _effect.emit(Effect.NavigateToMain)
            }
        }
    }
}