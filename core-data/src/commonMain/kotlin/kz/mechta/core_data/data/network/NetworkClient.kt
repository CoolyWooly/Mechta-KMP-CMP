package kz.mechta.core_data.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kz.mechta.core_data.domain.repository.AuthRepository

internal class NetworkClient(
    private val authRepository: AuthRepository
) {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                explicitNulls = false
            })
        }

        install(Auth) {
            bearer {
                loadTokens {
                    val token = authRepository.getToken().first()
                    if (token != null) {
                        BearerTokens(accessToken = token, refreshToken = null)
                    } else null
                }

//                refreshTokens {
//                    val newToken = refreshAuthToken()
//                    if (newToken != null) {
//                        tokenStorage.saveToken(newToken)
//                        BearerTokens(accessToken = newToken, refreshToken = null)
//                    } else null
//                }
            }
        }

        install(Logging) {
//            logger = Logger.ANDROID
//            level = LogLevel.ALL
        }

        defaultRequest {
            url("https://api.mechta.kz/")
            header("Content-Type", "application/json")
        }
    }

//    private suspend fun refreshAuthToken(): String? {
//        // Реализация обновления токена
//        return try {
//            val response: TokenResponse = httpClient.post("auth/refresh")
//            response.accessToken
//        } catch (e: Exception) {
//            null
//        }
//    }
}

@Serializable
internal data class TokenResponse(
    val accessToken: String,
    val refreshToken: String? = null
)
