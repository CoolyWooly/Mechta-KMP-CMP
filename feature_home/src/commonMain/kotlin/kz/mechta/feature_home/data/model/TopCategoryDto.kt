package kz.mechta.feature_home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kz.mechta.core_data.data.model.CategoryDto
import kz.mechta.core_data.data.model.ProductDto

@Serializable
data class TopCategoryDto(
    @SerialName("category")
    val category: CategoryDto?,
    @SerialName("products")
    val products: List<ProductDto>?
)

