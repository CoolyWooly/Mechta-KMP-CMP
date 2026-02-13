package kz.mechta.feature_home.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kz.mechta.core_data.data.model.base.BaseResponseDto
import kz.mechta.feature_home.data.model.BannerDto
import kz.mechta.feature_home.data.model.BrandDto
import kz.mechta.feature_home.data.model.ContactOptionsDto
import kz.mechta.feature_home.data.model.GetExpressDto
import kz.mechta.feature_home.data.model.GetNewsDto
import kz.mechta.feature_home.data.model.PopularCategoryDto
import kz.mechta.feature_home.data.model.PublicationDto
import kz.mechta.feature_home.data.model.TopCategoryDto

internal class HomeApi(
    private val client: HttpClient
) {
    suspend fun getBanners(
    ): List<BannerDto>? {
        return client.get("api/v3/publications/banners") {
        }.body()
    }

    suspend fun getPopularCategories(
    ): List<PopularCategoryDto>? {
        return client.get("api/v3/popular/categories") {
        }.body()
    }

    suspend fun getNews(
    ): BaseResponseDto<GetNewsDto?> {
        return client.get("api/v2/news") {
        }.body()
    }

    suspend fun getBrands(
    ): List<BrandDto>? {
        return client.get("api/v3/popular/brands") {
        }.body()
    }

    suspend fun subscribeEmail(
        email: String,
    ): BaseResponseDto<Unit?> {
        return client.submitForm("api/main/user_subscribe/index.php") {
            contentType(ContentType.Application.Json)
            setBody(FormDataContent(Parameters.build {
                append("email", email)
            }))
        }.body()
    }

    suspend fun getPublications(
        type: String? = null
    ): List<PublicationDto>? {
        return client.get("api/v3/publications") {
            parameter("type", type)
        }.body()
    }

    suspend fun getTopCategories(
        category: String? = null
    ): List<TopCategoryDto>? {
        return client.get("api/v3/popular/top-categories") {
            parameter("category", category)
        }.body()
    }

    suspend fun getContactOptions(): ContactOptionsDto? {
        return client.get("api/v3/footer").body()
    }

}