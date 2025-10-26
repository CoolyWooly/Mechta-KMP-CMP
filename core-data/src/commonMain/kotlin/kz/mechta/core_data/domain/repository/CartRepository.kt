package kz.mechta.core_data.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.mechta.core_data.domain.model.CartLocalModel
import kz.mechta.core_data.domain.model.Resource

interface CartRepository {
    suspend fun trackIds(): Flow<List<Int>>
    suspend fun getItem(productId: Int): CartLocalModel?
    suspend fun add(productId: Int, quantity: Int, gifts: String?, tradeIn: String?): Resource<Unit>
    suspend fun remove(productId: Int): Resource<Unit>
    suspend fun getBasket()
}