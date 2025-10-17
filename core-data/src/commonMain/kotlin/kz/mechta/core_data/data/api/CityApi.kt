package kz.mechta.core_data.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kz.mechta.core_data.data.model.BaseResponseDto
import kz.mechta.core_data.data.model.city.CityDto
import kz.mechta.core_data.data.model.city.GetCitiesDto

internal class CityApi(
    private val client: HttpClient
) {

    suspend fun getCities(): BaseResponseDto<GetCitiesDto>? {
        return client.get("api/v2/header/cities").body()
    }
}