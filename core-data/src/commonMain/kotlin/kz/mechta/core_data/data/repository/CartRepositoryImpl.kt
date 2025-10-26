package kz.mechta.core_data.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.mechta.core_data.data.api.CityApi
import kz.mechta.core_data.data.db.AppDatabase
import kz.mechta.core_data.data.mapper.CartMapper.toDomain
import kz.mechta.core_data.data.mapper.CityMapper.toDomain
import kz.mechta.core_data.data.utils.NetworkUtil
import kz.mechta.core_data.domain.model.CartLocalModel
import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.repository.CartRepository
import kz.mechta.core_data.domain.repository.CityRepository

internal class CartRepositoryImpl(
    private val networkUtil: NetworkUtil,
    private val appDatabase: AppDatabase
) : CartRepository {

    override suspend fun trackIds(): Flow<List<Int>> {
        return appDatabase.cartTableQueries.track()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.productId.toInt() } }
    }

    override suspend fun getItem(productId: Int): CartLocalModel? {
        val item = appDatabase.cartTableQueries.get(productId.toLong()).executeAsOneOrNull()
        return item?.toDomain()
    }

    override suspend fun add(
        productId: Int,
        quantity: Int,
        gifts: String?,
        tradeIn: String?
    ): Resource<Unit> {
        appDatabase.cartTableQueries.add(
            productId = productId.toLong(),
            quantity = quantity.toLong(),
            gifts = gifts,
            tradeIn = tradeIn
        )
        return Resource.Success(Unit)
    }

    override suspend fun remove(productId: Int): Resource<Unit> {
        appDatabase.cartTableQueries.remove(productId.toLong())
        return Resource.Success(Unit)
    }

    override suspend fun getBasket() {
        TODO("Not yet implemented")
    }

}