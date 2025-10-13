package kz.mechta.feature_onboarding.presentation

internal data class State(
    val currentPage: Int = 0,
    val isCompleted: Boolean = false
)

// События
internal sealed class Event {
    object NextPage : Event()
    object Finish : Event()
}

// Эффекты
internal sealed class Effect {
    object NavigateToHome : Effect()
}