package kz.mechta.feature_catalog.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kz.mechta.feature_catalog.data.model.BrandDto
import kz.mechta.feature_catalog.data.model.GetCatalogDto

internal class CatalogApi(
    private val client: HttpClient
) {
    suspend fun getCatalog(
    ): GetCatalogDto? {
        return client.get("api/v3/catalog/menu") {
        }.body()
    }

    suspend fun getBrands(
    ): List<BrandDto>? {
        return client.get("api/v3/popular/brands") {
        }.body()
    }
}