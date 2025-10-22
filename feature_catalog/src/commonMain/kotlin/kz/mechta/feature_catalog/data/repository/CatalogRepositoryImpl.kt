package kz.mechta.feature_catalog.data.repository

import kz.mechta.core_data.data.utils.NetworkUtil
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_catalog.data.mapper.CatalogMapper.toDomain
import kz.mechta.feature_catalog.data.network.CatalogApi
import kz.mechta.feature_catalog.domain.model.BrandModel
import kz.mechta.feature_catalog.domain.model.CatalogModel
import kz.mechta.feature_catalog.domain.repository.CatalogRepository

internal class CatalogRepositoryImpl(
    private val api: CatalogApi,
    private val networkUtil: NetworkUtil
) : CatalogRepository {

    override suspend fun getCatalog(): Resource<List<CatalogModel>> {
        val result = networkUtil.safeApiCall { api.getCatalog() }

        return when (result) {
            is Resource.Failure -> result
            is Resource.Success -> Resource.Success(result.value?.catalog?.map { it.toDomain() }.orEmpty())
        }
    }

    override suspend fun getBrands(): Resource<List<BrandModel>> {
        val result = networkUtil.safeApiCall { api.getBrands() }
        return when (result) {
            is Resource.Failure -> result
            is Resource.Success -> Resource.Success(result.value?.map { it.toDomain() }.orEmpty())
        }
    }
}