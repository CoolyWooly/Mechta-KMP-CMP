package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    @SerialName("averageRating") val averageRating: Float?,
    @SerialName("reviewsCount") val reviewsCount: Int?
)