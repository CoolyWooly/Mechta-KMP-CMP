package kz.mechta.core_data.data.mapper

import kz.mechta.core_data.data.model.city.CityDto
import kz.mechta.core_data.domain.model.CartLocalModel
import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.coredata.data.db.CartTable

internal object CartMapper {

    fun CartTable.toDomain() : CartLocalModel {
        return CartLocalModel(
            productId = this.productId.toInt(),
            quantity = this.quantity.toInt(),
            gifts = this.gifts,
            tradeIn = this.tradeIn
        )
    }
}