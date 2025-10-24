package kz.mechta.core_data.data.db

interface FavoritesDao {
    suspend fun add(id: Int)
    suspend fun remove(id: Int)
    suspend fun clear()
}