package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ContactOptionsDto(
    @SerialName("contacts")
    val contacts: ContactsDto?
)

@Serializable
internal data class ContactsDto(
    @SerialName("socials")
    val socials: List<SocialDto>?
)

@Serializable
internal data class SocialDto(
    @SerialName("icon")
    val icon: ThemedImageDto?,
    @SerialName("url")
    val url: String?
)

@Serializable
internal data class ThemedImageDto(
    @SerialName("dark") val dark: String?,
    @SerialName("light") val light: String?
)