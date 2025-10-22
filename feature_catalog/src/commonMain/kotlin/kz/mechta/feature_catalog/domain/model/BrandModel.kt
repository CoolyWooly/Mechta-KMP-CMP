package kz.mechta.feature_catalog.domain.model

internal data class BrandModel(
    val name: String,
    val image: BrandImage,
    val slug: String,
) {
    data class BrandImage(
        val light: String,
        val dark: String,
    )
}