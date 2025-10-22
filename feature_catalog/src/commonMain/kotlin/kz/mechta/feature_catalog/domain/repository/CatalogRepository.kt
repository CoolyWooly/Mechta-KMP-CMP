package kz.mechta.feature_catalog.domain.repository

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_catalog.domain.model.BrandModel
import kz.mechta.feature_catalog.domain.model.CatalogModel

internal interface CatalogRepository {

    suspend fun getCatalog(
    ): Resource<List<CatalogModel>>

    suspend fun getBrands(
    ): Resource<List<BrandModel>>
}