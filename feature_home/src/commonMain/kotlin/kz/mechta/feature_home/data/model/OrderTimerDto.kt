package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OrderTimerDto(
    @SerialName("status") val status: OrderStatusDto?,
    @SerialName("timer") val timer: Int?,
)