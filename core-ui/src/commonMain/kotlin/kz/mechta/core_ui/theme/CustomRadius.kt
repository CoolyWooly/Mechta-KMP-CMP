package kz.mechta.core_ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CustomRadius(
    val xs: Dp,
    val s: Dp,
    val m: Dp,
    val l: Dp,
    val xl: Dp,
    val xxl: Dp,
)

fun getRadius() : CustomRadius {
    return CustomRadius(
        xs = 3.dp,
        s = 5.dp,
        m = 6.dp,
        l = 8.dp,
        xl = 10.dp,
        xxl = 16.dp
    )
}
