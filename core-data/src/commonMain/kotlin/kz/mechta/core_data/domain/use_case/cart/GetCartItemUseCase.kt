package kz.mechta.core_data.domain.use_case.cart

import kz.mechta.core_data.domain.model.CartLocalModel
import kz.mechta.core_data.domain.repository.CartRepository

class GetCartItemUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(productId: Int): CartLocalModel? {
        return cartRepository.getItem(productId)
    }
}