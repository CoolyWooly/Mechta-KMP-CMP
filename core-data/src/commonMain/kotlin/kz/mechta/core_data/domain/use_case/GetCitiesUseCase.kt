package kz.mechta.core_data.domain.use_case

import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.repository.CityRepository

class GetCitiesUseCase(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(): Resource<List<CityModel>> {
        return cityRepository.getCities()
    }
}