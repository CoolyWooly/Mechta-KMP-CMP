package kz.mechta.core_data.data.model.city

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kz.mechta.core_data.data.model.CoordinatesDto

@Serializable
internal data class CityDto(
    @SerialName("code") val code: String?,
    @SerialName("kaspi_code") val kaspiCode: String?,
    @SerialName("name") val name: String?,
    @SerialName("phones") val phones: List<String>?,
    @SerialName("coordinates") val coordinates: CoordinatesDto?,
    @SerialName("translit") val translit: String?,
)