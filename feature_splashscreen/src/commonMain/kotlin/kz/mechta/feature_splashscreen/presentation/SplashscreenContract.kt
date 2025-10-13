package kz.mechta.feature_splashscreen.presentation

internal data class State(
    val isLoading: Boolean = false
)

// События
internal sealed class Event {
}

// Эффекты
internal sealed class Effect {
    object NavigateToMain : Effect()
    object NavigateToOnboarding : Effect()
}