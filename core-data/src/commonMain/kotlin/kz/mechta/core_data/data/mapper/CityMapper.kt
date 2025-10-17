package kz.mechta.core_data.data.mapper

import kz.mechta.core_data.data.model.city.CityDto
import kz.mechta.core_data.domain.model.CityModel

internal object CityMapper {

    fun CityDto.toDomain() : CityModel {
        return CityModel(
            code = this.code.orEmpty(),
            kaspiCode = this.kaspiCode.orEmpty(),
            name = this.name.orEmpty(),
            phones = this.phones.orEmpty(),
            latitude = this.coordinates?.latitude,
            longitude = this.coordinates?.longitude,
            translit = this.translit.orEmpty()
        )
    }
}