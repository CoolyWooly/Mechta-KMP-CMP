package kz.mechta.core_data.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.mechta.core_data.domain.repository.AuthRepository

internal class AuthRepositoryImpl(
    private val datastore: DataStore<Preferences>
) : AuthRepository {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    override suspend fun saveToken(token: String) {
        datastore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun getToken(): Flow<String?> {
        return datastore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    override suspend fun clearToken() {
        datastore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}