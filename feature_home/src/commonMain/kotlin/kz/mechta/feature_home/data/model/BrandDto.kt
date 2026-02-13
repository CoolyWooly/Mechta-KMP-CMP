package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BrandDto(
    @SerialName("image") val image: ImageDto?,
    @SerialName("name") val name: String?,
    @SerialName("slug") val slug: String?,
)

@Serializable
internal data class ImageDto(
    @SerialName("dark") val dark: String?,
    @SerialName("light") val light: String?
)