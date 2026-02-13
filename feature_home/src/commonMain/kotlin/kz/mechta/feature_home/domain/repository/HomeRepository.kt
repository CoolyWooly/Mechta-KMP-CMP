package kz.mechta.feature_home.domain.repository

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.domain.model.BannerModel
import kz.mechta.feature_home.domain.model.BrandModel
import kz.mechta.feature_home.domain.model.CategoryModel
import kz.mechta.feature_home.domain.model.NewsModel
import kz.mechta.feature_home.domain.model.PublicationModel
import kz.mechta.feature_home.domain.model.SocialModel
import kz.mechta.feature_home.domain.model.TopCategoryModel

internal interface HomeRepository {
    suspend fun getBanners(): Resource<List<BannerModel>>
    suspend fun getPopularCategories(): Resource<List<CategoryModel>>
    suspend fun getBrands(): Resource<List<BrandModel>>
    suspend fun getPublications(type: String? = null): Resource<List<PublicationModel>>
    suspend fun getNews(): Resource<List<NewsModel>>
    suspend fun getTopCategories(category: String? = null): Resource<List<TopCategoryModel>>
    suspend fun getSocials(): Resource<List<SocialModel>>
    suspend fun subscribeEmail(email: String): Resource<Unit>
}
