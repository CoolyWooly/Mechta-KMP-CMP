package kz.mechta.core_data.domain.repository

import kotlinx.serialization.Serializable
import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_data.domain.model.Resource

interface CityRepository {
    suspend fun getCities(): Resource<List<CityModel>>
}