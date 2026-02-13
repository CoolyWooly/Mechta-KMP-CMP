package kz.mechta.feature_home.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.domain.model.BrandModel
import kz.mechta.feature_home.domain.repository.HomeRepository

internal class GetBrandsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<List<BrandModel>> {
        return repository.getBrands()
    }
}
