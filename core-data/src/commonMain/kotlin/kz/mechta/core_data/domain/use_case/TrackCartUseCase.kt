package kz.mechta.core_data.domain.use_case

import kotlinx.coroutines.flow.Flow
import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.repository.CartRepository
import kz.mechta.core_data.domain.repository.CityRepository

class TrackCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Flow<List<Int>> {
        return cartRepository.trackIds()
    }
}