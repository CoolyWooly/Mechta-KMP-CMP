package kz.mechta.core_data.domain.use_case.city

import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_data.domain.repository.CityRepository

class GetCityUseCase(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(): CityModel? {
        return cityRepository.getCity()
    }
}