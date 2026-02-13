package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertyDto(
    @SerialName("name") val name: String?,
    @SerialName("url") val url: String?,
    @SerialName("value") val value: String?,
)