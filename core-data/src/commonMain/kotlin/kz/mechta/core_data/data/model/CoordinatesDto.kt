package kz.mechta.core_data.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
internal data class CoordinatesDto (
    @JsonNames("latitude")
    @SerialName("lat")
    val latitude: Double?,

    @JsonNames("longitude")
    @SerialName("lon")
    val longitude: Double?,
)