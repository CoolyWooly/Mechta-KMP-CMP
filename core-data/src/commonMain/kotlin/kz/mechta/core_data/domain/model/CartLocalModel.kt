package kz.mechta.core_data.domain.model

data class CartLocalModel(
    val productId: Int,
    val quantity: Int,
    val gifts: String?,
    val tradeIn: String?
)
