package kz.mechta.feature_home.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.domain.repository.HomeRepository

internal class SubscribeEmailUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(email: String): Resource<Unit> {
        return repository.subscribeEmail(email)
    }
}
