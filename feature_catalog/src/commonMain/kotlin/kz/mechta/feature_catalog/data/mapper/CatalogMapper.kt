package kz.mechta.feature_catalog.data.mapper

import kz.mechta.feature_catalog.data.model.BrandDto
import kz.mechta.feature_catalog.data.model.CategoryDto
import kz.mechta.feature_catalog.domain.model.BrandModel
import kz.mechta.feature_catalog.domain.model.CatalogModel

internal object CatalogMapper {

    fun CategoryDto.toDomain(): CatalogModel {
        return CatalogModel(
            name = this.name.orEmpty(),
            image = this.image.orEmpty(),
            url = this.url.orEmpty()
        )
    }

    fun BrandDto.toDomain(): BrandModel {
        return BrandModel(
            name = this.name.orEmpty(),
            image = this.image.toDomain(),
            slug = this.slug.orEmpty()
        )
    }

    fun BrandDto.BrandImageDto?.toDomain(): BrandModel.BrandImage {
        return BrandModel.BrandImage(
            dark = this?.dark.orEmpty(),
            light = this?.light.orEmpty(),
        )
    }
}