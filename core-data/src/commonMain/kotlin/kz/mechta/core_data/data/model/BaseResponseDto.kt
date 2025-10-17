package kz.mechta.core_data.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class BaseResponseDto<T>(
    @JsonNames("result", "success")
    @SerialName("result") val result: Boolean,
    @SerialName("errors") val errors: List<String>? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: T? = null
)
