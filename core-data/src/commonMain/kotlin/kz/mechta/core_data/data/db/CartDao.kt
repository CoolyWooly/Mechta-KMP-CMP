package kz.mechta.core_data.data.db

interface CartDao {
    suspend fun add(productId: Int, quantity: Int, gifts: String?, tradeIn: String?)
    suspend fun remove(productId: Int)
    suspend fun clear()
}