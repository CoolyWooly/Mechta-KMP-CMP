package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PricesDto(
    @SerialName("basePrice") val basePrice: Int?,
    @SerialName("finalPrice") val finalPrice: Int?,
)