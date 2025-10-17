package kz.mechta.core_data.domain.repository

interface PrefsRepository {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun clearToken()
}