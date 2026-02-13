package kz.mechta.feature_onboarding.presentation

import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_ui.utils.UnidirectionalViewModel

internal interface OnboardingContract :
    UnidirectionalViewModel<OnboardingContract.State, OnboardingContract.Event, OnboardingContract.Effect> {
    data class State(
        val cities: List<CityModel> = emptyList(),
        val filteredCities: List<CityModel> = emptyList(),
        val searchQuery: String = "",
        val isLoading: Boolean = true
    )

    // События
    sealed class Event {
        data class OnCityClick(val cityModel: CityModel) : Event()
        data class OnSearchQueryChanged(val query: String) : Event()
    }

    // Эффекты
    sealed class Effect {
        object NavigateToMain : Effect()
    }
}