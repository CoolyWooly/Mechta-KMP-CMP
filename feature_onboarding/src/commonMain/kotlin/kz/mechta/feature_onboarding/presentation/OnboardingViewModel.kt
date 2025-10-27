package kz.mechta.feature_onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.use_case.city.GetCitiesUseCase
import kz.mechta.core_data.domain.use_case.city.SaveCityUseCase

internal class OnboardingViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val saveCityUseCase: SaveCityUseCase,
) : ViewModel(), OnboardingContract {
    private val mutableState = MutableStateFlow(OnboardingContract.State())
    override val state: StateFlow<OnboardingContract.State> = mutableState.asStateFlow()

    private val effectFlow = MutableSharedFlow<OnboardingContract.Effect>()
    override val effect: SharedFlow<OnboardingContract.Effect> = effectFlow.asSharedFlow()

    override fun event(event: OnboardingContract.Event) {
        when (event) {
            is OnboardingContract.Event.OnCityClick -> onCityClick(event.cityModel)
        }
    }

    init {
        getCities()
    }

    private fun getCities() {
        viewModelScope.launch {
            when (val data = getCitiesUseCase()) {
                is Resource.Failure -> {

                }
                is Resource.Success -> {
                    mutableState.update {
                        it.copy(cities = data.value)
                    }
                }
            }
        }
    }

    private fun onCityClick(cityModel: CityModel) {
        viewModelScope.launch {
            saveCityUseCase(cityModel)
            effectFlow.emit(OnboardingContract.Effect.NavigateToMain)
        }
    }
}