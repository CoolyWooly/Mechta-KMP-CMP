package kz.mechta.core_data.domain.use_case.cart

import kotlinx.coroutines.flow.Flow
import kz.mechta.core_data.domain.repository.CartRepository

class TrackCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Flow<List<Int>> {
        return cartRepository.trackIds()
    }
}