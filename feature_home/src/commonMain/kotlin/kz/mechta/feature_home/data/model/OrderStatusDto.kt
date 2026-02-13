package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OrderStatusDto(
    @SerialName("code") val code: String?,
    @SerialName("name") val name: String?,
)