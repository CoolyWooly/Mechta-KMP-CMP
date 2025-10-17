package kz.mechta.core_data.data.model.city

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kz.mechta.core_data.data.model.CoordinatesDto

@Serializable
internal data class GetCitiesDto(
    @SerialName("cities") val cities: List<CityDto>?,
    @SerialName("current_city") val currentCity: String?,
)