package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StickerDto(
    @SerialName("code") val code: String?,
    @SerialName("name") val name: String?,
    @SerialName("link") val link: String?,
    @SerialName("color") val color: String?,
    @SerialName("isSuperAction") val isSuperAction: Boolean?,
    @SerialName("description") val description: String?,
)