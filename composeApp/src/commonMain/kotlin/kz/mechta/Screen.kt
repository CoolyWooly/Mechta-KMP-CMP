package kz.mechta

import cafe.adriel.voyager.core.registry.ScreenProvider
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class Screen : ScreenProvider {
    object PostList : Screen()
    object Onboarding : Screen()
}