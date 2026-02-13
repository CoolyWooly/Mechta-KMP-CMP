package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BannerDto(
    @SerialName("name") val name: String?,
    @SerialName("url") val url: String?,
    @SerialName("mobile") val image: String?
)