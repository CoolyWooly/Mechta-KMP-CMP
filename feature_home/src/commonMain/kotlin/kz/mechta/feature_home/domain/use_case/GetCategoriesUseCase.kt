package kz.mechta.feature_home.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.domain.model.CategoryModel
import kz.mechta.feature_home.domain.repository.HomeRepository

internal class GetCategoriesUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<List<CategoryModel>> {
        return repository.getPopularCategories()
    }
}
