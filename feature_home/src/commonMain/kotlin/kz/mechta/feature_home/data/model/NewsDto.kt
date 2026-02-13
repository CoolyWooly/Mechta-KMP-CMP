package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NewsDto(
    @SerialName("active_from") val activeFrom: String?,
    @SerialName("active_to") val activeTo: String?,
    @SerialName("brand_logo") val brandLogo: String?,
    @SerialName("code") val code: String?,
    @SerialName("days_before_expiration") val daysBeforeExpiration: Int?,
    @SerialName("entity") val entity: String?,
    @SerialName("id") val id: Int?,
    @SerialName("image_src") val imageSrc: String?,
    @SerialName("link") val link: LinkDto?,
    @SerialName("name") val name: String?,
    @SerialName("preview_text") val previewText: String?,
    @SerialName("sort") val sort: Int?
) {
    @Serializable
    internal data class LinkDto(
        @SerialName("is_external") val isExternal: Boolean?,
        @SerialName("value") val value: String?
    )
}