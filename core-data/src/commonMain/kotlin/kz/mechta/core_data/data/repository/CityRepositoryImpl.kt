package kz.mechta.core_data.data.repository

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kz.mechta.core_data.data.api.CityApi
import kz.mechta.core_data.data.mapper.CityMapper.toDomain
import kz.mechta.core_data.data.utils.NetworkUtil
import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.repository.CityRepository

internal class CityRepositoryImpl(
    private val networkUtil: NetworkUtil,
    private val api: CityApi,
) : CityRepository {

    override suspend fun getCities(): Resource<List<CityModel>> {
        val result = networkUtil.safeApiCall { api.getCities() }
        return when (result) {
            is Resource.Success -> {
                val data = result.value?.data
                if (data != null) {
                    Resource.Success(data.cities?.map { it.toDomain() }.orEmpty())
                } else {
                    Resource.Failure.FailureServer()
                }
            }
            is Resource.Failure -> result
        }
    }
}