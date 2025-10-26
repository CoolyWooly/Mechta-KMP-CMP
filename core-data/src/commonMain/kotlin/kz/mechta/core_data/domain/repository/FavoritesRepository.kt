package kz.mechta.core_data.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun saveToken(token: String)
    suspend fun getToken(): Flow<String?>
    suspend fun clearToken()
}