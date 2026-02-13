package kz.mechta.feature_home.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.domain.model.NewsModel
import kz.mechta.feature_home.domain.repository.HomeRepository

internal class GetNewsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<List<NewsModel>> {
        return repository.getNews()
    }
}
