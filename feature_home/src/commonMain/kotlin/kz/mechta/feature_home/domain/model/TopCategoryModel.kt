package kz.mechta.feature_home.domain.model

internal data class TopCategoryModel(
    val categoryName: String,
    val categorySlug: String,
    val products: List<ProductModel>
)

internal data class ProductModel(
    val id: String,
    val name: String,
    val slug: String,
    val code: String,
    val preview: String,
    val price: Int?,
    val oldPrice: Int?,
    val availability: String,
    val rating: Double?,
    val reviewsCount: Int?
)
