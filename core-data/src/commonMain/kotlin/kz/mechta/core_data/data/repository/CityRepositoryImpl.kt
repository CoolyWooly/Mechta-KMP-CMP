package kz.mechta.core_data.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kz.mechta.core_data.data.api.CityApi
import kz.mechta.core_data.data.mapper.CityMapper.toDomain
import kz.mechta.core_data.data.mapper.CityMapper.toDto
import kz.mechta.core_data.data.model.city.CityDto
import kz.mechta.core_data.data.utils.NetworkUtil
import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.repository.CityRepository

internal class CityRepositoryImpl(
    private val networkUtil: NetworkUtil,
    private val api: CityApi,
    private val dataStore: DataStore<Preferences>,
) : CityRepository {

    private val json = Json { ignoreUnknownKeys = true }
    private val CITY_KEY = stringPreferencesKey("CITY_KEY")

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

    override suspend fun saveCity(cityModel: CityModel) {
        dataStore.edit { prefs ->
            prefs[CITY_KEY] = json.encodeToString(cityModel.toDto())
        }
    }

    override suspend fun getCity(): CityModel? {
        val jsonString = dataStore.data
            .map { it[CITY_KEY] }
            .firstOrNull()
        return jsonString?.let {
            runCatching { json.decodeFromString<CityDto>(it) }.getOrNull()?.toDomain()
        }
    }
}