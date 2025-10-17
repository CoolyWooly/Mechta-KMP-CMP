package kz.mechta.core_data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ErrorResponseDto(
    @SerialName("error") val error: ErrorData?,
) {
    @Serializable
    internal data class ErrorData(
        @SerialName("code") val code: String?,
        @SerialName("message") val message: String?,
    )
}