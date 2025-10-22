package kz.mechta.feature_catalog.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CategoryDto(
    @SerialName("name") val name: String?,
    @SerialName("url") val url: String?,
    @SerialName("image") val image: String?,
)
