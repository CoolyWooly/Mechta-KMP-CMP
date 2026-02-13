package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetExpressDto(
    @SerialName("orders") val orders: List<OrderDto>?,
)