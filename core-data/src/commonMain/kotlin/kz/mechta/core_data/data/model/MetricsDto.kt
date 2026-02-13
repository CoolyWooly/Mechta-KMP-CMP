package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetricsDto(
    @SerialName("name") val name: String?,
    @SerialName("brand") val brand: String?,
    @SerialName("category") val category: String?
)
