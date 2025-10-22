package kz.mechta.feature_catalog.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_catalog.domain.model.CatalogModel
import kz.mechta.feature_catalog.domain.repository.CatalogRepository

internal class GetCatalogUseCase(
    private val catalogRepository: CatalogRepository,
) {

    suspend operator fun invoke(
    ): Resource<List<CatalogModel>> {
        return catalogRepository.getCatalog()
    }
}