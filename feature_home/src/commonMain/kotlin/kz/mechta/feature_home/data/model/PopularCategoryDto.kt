package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PopularCategoryDto(
    @SerialName("title") val title: String?,
    @SerialName("image") val image: String?,
    @SerialName("url") val url: String?,
)