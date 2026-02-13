package kz.mechta.feature_home.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PublicationDto(
    @SerialName("type") val type: String?,
    @SerialName("image") val image: String?,
    @SerialName("slug") val slug: String?,
    @SerialName("title") val title: String?,
    @SerialName("previewText") val previewText: String?,
    @SerialName("fromDate") val fromDate: String?,
    @SerialName("toDate") val toDate: String?
)
