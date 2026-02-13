package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertyGroupDto(
    @SerialName("name") val name: String?,
    @SerialName("properties") val properties: List<PropertyDto>?
)