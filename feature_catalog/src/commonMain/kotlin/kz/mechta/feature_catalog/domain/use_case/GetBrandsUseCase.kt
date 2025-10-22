package kz.mechta.feature_catalog.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_catalog.domain.model.BrandModel
import kz.mechta.feature_catalog.domain.repository.CatalogRepository

internal class GetBrandsUseCase(
    private val catalogRepository: CatalogRepository,
) {

    suspend operator fun invoke(
    ): Resource<List<BrandModel>> {
        return catalogRepository.getBrands()
    }
}