package kz.mechta.core_data.data.utils

import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNames
import kz.mechta.core_data.data.model.ErrorResponseDto
import kz.mechta.core_data.domain.model.Resource

internal object NetworkUtil {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall())
            } catch (throwable: Throwable) {
                handleFailure(throwable = throwable)
            }
        }
    }
}

private suspend fun handleFailure(throwable: Throwable): Resource.Failure {
    throwable.printStackTrace()
    return when (throwable) {
        is ClientRequestException -> {
            Resource.Failure.FailureServer(
                message =  convertErrorBody(throwable.response),
            )
        }
        is ServerResponseException -> {
            Resource.Failure.FailureServer(
                message =  convertErrorBody(throwable.response),
            )
        }
        is SocketTimeoutException -> {
            Resource.Failure.FailureServer(
                message = "Превышено время ожидания запроса"
            )
        }

        else -> Resource.Failure.FailureNetwork
    }
}

private suspend fun convertErrorBody(response: HttpResponse): String? {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }
    val jsonText = response.bodyAsText()
    return try {
        val error = json.decodeFromString<ErrorResponseDto>(jsonText)
        error.error?.message
    } catch (exception: Exception) {
        null
    }
}