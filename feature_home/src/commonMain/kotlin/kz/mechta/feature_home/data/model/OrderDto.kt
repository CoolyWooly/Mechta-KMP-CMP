package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OrderDto(
    @SerialName("id") val id: Int?,
    @SerialName("status") val status: OrderStatusDto?,
    @SerialName("express_delivery") val expressDelivery: OrderTimerDto?,
    @SerialName("aggregator") val aggregator: String?,
)