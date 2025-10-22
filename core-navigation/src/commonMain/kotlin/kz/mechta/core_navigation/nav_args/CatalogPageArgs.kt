package kz.mechta.core_navigation.nav_args

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatalogPageArgs(
//    @SerialName("source") val source: EventSource,
    @SerialName("tabIndex") val tabIndex: Int = 0,
)
