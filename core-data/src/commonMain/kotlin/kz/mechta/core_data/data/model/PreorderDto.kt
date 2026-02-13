package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreorderDto(
    @SerialName("activeFrom") val activeFrom: String?,
    @SerialName("activeTo") val activeTo: String?,
    @SerialName("sellFrom") val sellFrom: String?,
)