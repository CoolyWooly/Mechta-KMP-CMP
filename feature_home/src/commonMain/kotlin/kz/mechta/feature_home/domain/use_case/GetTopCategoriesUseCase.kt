package kz.mechta.feature_home.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.domain.model.TopCategoryModel
import kz.mechta.feature_home.domain.repository.HomeRepository

internal class GetTopCategoriesUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(category: String? = null): Resource<List<TopCategoryModel>> {
        return repository.getTopCategories(category)
    }
}
