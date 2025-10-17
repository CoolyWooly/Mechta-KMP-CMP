package kz.mechta.core_data.data.repository

import com.russhwolf.settings.Settings
import kz.mechta.core_data.domain.repository.PrefsRepository

internal class PrefsRepositoryImpl(
    private val settings: Settings
) : PrefsRepository {
    companion object {
        private const val TOKEN_KEY = "auth_token"
    }

    override suspend fun saveToken(token: String) {
        settings.putString(TOKEN_KEY, token)
    }

    override suspend fun getToken(): String? {
        return settings.getStringOrNull(TOKEN_KEY)
    }

    override suspend fun clearToken() {
        settings.remove(TOKEN_KEY)
    }
}