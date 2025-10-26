package kz.mechta.core_data.domain.use_case

import kz.mechta.core_data.domain.model.CartLocalModel
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.repository.CartRepository

class AddCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(
        productId: Int,
        quantity: Int,
        gifts: String? = null,
        tradeIn: String? = null
    ): Resource<Unit> {
        return cartRepository.add(
            productId = productId,
            quantity = quantity,
            gifts = gifts,
            tradeIn = tradeIn
        )
    }
}