package kz.mechta.core_data.domain.model

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    sealed class Failure() : Resource<Nothing>() {
        data object FailureNetwork: Failure()
        data class FailureServer(val message: String? = null): Failure()
    }
}