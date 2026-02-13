package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShipmentDto(
    @SerialName("todayDelivery") val todayDelivery: Boolean?,
    @SerialName("expressDelivery") val expressDelivery: Boolean?,
    @SerialName("pickupAvailable") val pickupAvailable: Boolean?,
    @SerialName("subdivisions") val subdivisions: Int?,
)