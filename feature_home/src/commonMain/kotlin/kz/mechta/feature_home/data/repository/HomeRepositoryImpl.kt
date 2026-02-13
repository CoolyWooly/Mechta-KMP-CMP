package kz.mechta.feature_home.data.repository

import kz.mechta.core_data.data.utils.NetworkUtil
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.data.mapper.HomeMapper.toDomain
import kz.mechta.feature_home.data.mapper.HomeMapper.toSocials
import kz.mechta.feature_home.data.network.HomeApi
import kz.mechta.feature_home.domain.model.BannerModel
import kz.mechta.feature_home.domain.model.BrandModel
import kz.mechta.feature_home.domain.model.CategoryModel
import kz.mechta.feature_home.domain.model.NewsModel
import kz.mechta.feature_home.domain.model.PublicationModel
import kz.mechta.feature_home.domain.model.SocialModel
import kz.mechta.feature_home.domain.model.TopCategoryModel
import kz.mechta.feature_home.domain.repository.HomeRepository

internal class HomeRepositoryImpl(
    private val api: HomeApi
) : HomeRepository {

    override suspend fun getBanners(): Resource<List<BannerModel>> {
        return when (val result = NetworkUtil.safeApiCall { api.getBanners() }) {
            is Resource.Success -> {
                val data = result.value?.mapNotNull { it.toDomain() }.orEmpty()
                Resource.Success(data)
            }
            is Resource.Failure -> result
        }
    }

    override suspend fun getPopularCategories(): Resource<List<CategoryModel>> {
        return when (val result = NetworkUtil.safeApiCall { api.getPopularCategories() }) {
            is Resource.Success -> {
                val data = result.value?.mapNotNull { it.toDomain() }.orEmpty()
                Resource.Success(data)
            }
            is Resource.Failure -> result
        }
    }

    override suspend fun getBrands(): Resource<List<BrandModel>> {
        return when (val result = NetworkUtil.safeApiCall { api.getBrands() }) {
            is Resource.Success -> {
                val data = result.value?.mapNotNull { it.toDomain() }.orEmpty()
                Resource.Success(data)
            }
            is Resource.Failure -> result
        }
    }

    override suspend fun getPublications(type: String?): Resource<List<PublicationModel>> {
        return when (val result = NetworkUtil.safeApiCall { api.getPublications(type) }) {
            is Resource.Success -> {
                val data = result.value?.mapNotNull { it.toDomain() }.orEmpty()
                Resource.Success(data)
            }
            is Resource.Failure -> result
        }
    }

    override suspend fun getNews(): Resource<List<NewsModel>> {
        return when (val result = NetworkUtil.safeApiCall { api.getNews() }) {
            is Resource.Success -> {
                val data = result.value?.data?.news?.mapNotNull { it.toDomain() }.orEmpty()
                Resource.Success(data)
            }
            is Resource.Failure -> result
        }
    }

    override suspend fun getTopCategories(category: String?): Resource<List<TopCategoryModel>> {
        return when (val result = NetworkUtil.safeApiCall { api.getTopCategories(category) }) {
            is Resource.Success -> {
                val data = result.value?.mapNotNull { it.toDomain() }.orEmpty()
                Resource.Success(data)
            }
            is Resource.Failure -> result
        }
    }

    override suspend fun getSocials(): Resource<List<SocialModel>> {
        return when (val result = NetworkUtil.safeApiCall { api.getContactOptions() }) {
            is Resource.Success -> {
                val data = result.value?.toSocials().orEmpty()
                Resource.Success(data)
            }
            is Resource.Failure -> result
        }
    }

    override suspend fun subscribeEmail(email: String): Resource<Unit> {
        return when (val result = NetworkUtil.safeApiCall { api.subscribeEmail(email) }) {
            is Resource.Success -> Resource.Success(Unit)
            is Resource.Failure -> result
        }
    }
}
