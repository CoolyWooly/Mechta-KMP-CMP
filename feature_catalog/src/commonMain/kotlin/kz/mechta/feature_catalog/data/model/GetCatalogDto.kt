package kz.mechta.feature_catalog.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetCatalogDto(
    @SerialName("catalog") val catalog: List<CategoryDto>?,
)
