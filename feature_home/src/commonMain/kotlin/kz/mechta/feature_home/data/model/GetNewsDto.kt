package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetNewsDto(
    @SerialName("news") val news: List<NewsDto>,
)