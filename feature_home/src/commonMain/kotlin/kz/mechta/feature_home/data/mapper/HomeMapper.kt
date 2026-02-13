package kz.mechta.feature_home.data.mapper

import kz.mechta.core_data.data.model.ProductDto
import kz.mechta.feature_home.data.model.BannerDto
import kz.mechta.feature_home.data.model.BrandDto
import kz.mechta.feature_home.data.model.ContactOptionsDto
import kz.mechta.feature_home.data.model.NewsDto
import kz.mechta.feature_home.data.model.PopularCategoryDto
import kz.mechta.feature_home.data.model.PublicationDto
import kz.mechta.feature_home.data.model.SocialDto
import kz.mechta.feature_home.data.model.TopCategoryDto
import kz.mechta.feature_home.domain.model.BannerModel
import kz.mechta.feature_home.domain.model.BrandModel
import kz.mechta.feature_home.domain.model.CategoryModel
import kz.mechta.feature_home.domain.model.NewsModel
import kz.mechta.feature_home.domain.model.ProductModel
import kz.mechta.feature_home.domain.model.PublicationModel
import kz.mechta.feature_home.domain.model.SocialModel
import kz.mechta.feature_home.domain.model.TopCategoryModel

internal object HomeMapper {
    fun BannerDto.toDomain(): BannerModel? {
        return BannerModel(
            name = name ?: return null,
            url = url.orEmpty(),
            image = image ?: return null
        )
    }

    fun PopularCategoryDto.toDomain(): CategoryModel? {
        return CategoryModel(
            title = title ?: return null,
            image = image ?: return null,
            url = url.orEmpty()
        )
    }

    fun BrandDto.toDomain(): BrandModel? {
        return BrandModel(
            name = name ?: return null,
            slug = slug.orEmpty(),
            imageDark = image?.dark.orEmpty(),
            imageLight = image?.light.orEmpty()
        )
    }

    fun PublicationDto.toDomain(): PublicationModel? {
        return PublicationModel(
            type = type ?: return null,
            image = image ?: return null,
            slug = slug.orEmpty(),
            title = title ?: return null,
            previewText = previewText.orEmpty(),
            fromDate = fromDate,
            toDate = toDate
        )
    }

    fun NewsDto.toDomain(): NewsModel? {
        return NewsModel(
            id = id ?: return null,
            name = name ?: return null,
            code = code.orEmpty(),
            imageSrc = imageSrc ?: return null,
            previewText = previewText.orEmpty(),
            brandLogo = brandLogo,
            activeFrom = activeFrom,
            activeTo = activeTo,
            daysBeforeExpiration = daysBeforeExpiration,
            linkUrl = link?.value,
            isExternalLink = link?.isExternal ?: false
        )
    }

    fun TopCategoryDto.toDomain(): TopCategoryModel? {
        return TopCategoryModel(
            categoryName = category?.name ?: return null,
            categorySlug = category.slug.orEmpty(),
            products = products?.mapNotNull { it.toDomain() }.orEmpty()
        )
    }

    fun ProductDto.toDomain(): ProductModel? {
        return ProductModel(
            id = id ?: return null,
            name = name ?: return null,
            slug = slug.orEmpty(),
            code = code.orEmpty(),
            preview = preview.orEmpty(),
            price = prices?.finalPrice,
            oldPrice = prices?.basePrice,
            availability = availability.orEmpty(),
            rating = rating?.averageRating?.toDouble(),
            reviewsCount = rating?.reviewsCount
        )
    }

    fun SocialDto.toDomain(): SocialModel? {
        return SocialModel(
            url = url ?: return null,
            iconDark = icon?.dark.orEmpty(),
            iconLight = icon?.light.orEmpty()
        )
    }

    fun ContactOptionsDto.toSocials(): List<SocialModel> {
        return contacts?.socials?.mapNotNull { it.toDomain() }.orEmpty()
    }
}
