package kz.mechta.core_data.domain.model

data class CityModel(
    val code: String,
    val kaspiCode: String,
    val name: String,
    val phones: List<String>,
    val latitude: Double?,
    val longitude: Double?,
    val translit: String,
)
