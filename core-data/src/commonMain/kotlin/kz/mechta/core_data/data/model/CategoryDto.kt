package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("name") val name: String?,
    @SerialName("slug") val slug: String?,
    @SerialName("level") val level: Int?
)