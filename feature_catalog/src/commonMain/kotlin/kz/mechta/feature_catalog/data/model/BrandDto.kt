package kz.mechta.feature_catalog.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BrandDto(
    @SerialName("name") val name: String?,
    @SerialName("slug") val slug: String?,
    @SerialName("image") val image: BrandImageDto?,
) {
    @Serializable
    data class BrandImageDto(
        @SerialName("dark") val dark: String?,
        @SerialName("light") val light: String?,
    )
}
