package kz.mechta.feature_home.domain.use_case

import kz.mechta.core_data.domain.model.Resource
import kz.mechta.feature_home.domain.model.PublicationModel
import kz.mechta.feature_home.domain.repository.HomeRepository

internal class GetPublicationsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(type: String? = null): Resource<List<PublicationModel>> {
        return repository.getPublications(type)
    }
}
