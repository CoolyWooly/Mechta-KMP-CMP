package kz.mechta.feature_home.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.domain.model.BannerModel
import kz.mechta.feature_home.domain.repository.HomeRepository

internal class GetBannersUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<List<BannerModel>> {
        return repository.getBanners()
    }
}
