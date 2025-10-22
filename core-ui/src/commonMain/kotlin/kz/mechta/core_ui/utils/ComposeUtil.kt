package kz.mechta.core_ui.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.node.Ref
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kz.mechta.core_ui.theme.MechtaTheme
import kotlin.math.min
import kotlin.math.pow

//@Composable
//@ReadOnlyComposable
//fun quantityStringResource(@PluralsRes id: Int, quantity: Int, vararg args: Any?): String {
//    val resources = resources()
//    return resources.getQuantityString(id, quantity, *args)
//}

@Composable
fun SpacerStatusBar() {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Spacer(modifier = Modifier.height(statusBarHeight))
}

@Composable
fun SpacerNavigationBar() {
    val statusBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    Spacer(modifier = Modifier.height(statusBarHeight))
}

fun Modifier.shimmerEffect(): Modifier = composed {
    val themeColors = MechtaTheme.colors
    val colors = remember(themeColors.textPrimary, themeColors.textSecondary) { listOf(themeColors.textSecondary, themeColors.textPrimary, themeColors.textSecondary) }

    val progress by rememberInfiniteTransition()
        .animateFloat(
            initialValue = -2f,
            targetValue = 2f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000)
            )
        )

    drawWithCache {
        onDrawWithContent {
            val w = size.width
            val h = size.height
            val startX = w * progress
            val brush = Brush.linearGradient(
                colors = colors,
                start = Offset(startX, 0f),
                end = Offset(startX + w, h)
            )
            drawRect(brush = brush)
        }
    }
}

//fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
//    Modifier.drawBehind {
//        val angleRad = angle / 180f * Math.PI
//        val x = kotlin.math.cos(angleRad).toFloat() //Fractional x
//        val y = kotlin.math.sin(angleRad).toFloat() //Fractional y
//
//        val radius:Float = kotlin.math.sqrt(
//            ((size.width.pow(2) + size.height.pow(2))) / 2f)
//        val offset = center + Offset(x * radius, y * radius)
//
//        val exactOffset = Offset(
//            x = min(offset.x.coerceAtLeast(0f), size.width),
//            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
//        )
//
//        drawRect(
//            brush = Brush.linearGradient(
//                colors = colors,
//                start = Offset(size.width, size.height) - exactOffset,
//                end = exactOffset
//            ),
//            size = size
//        )
//    }
//)

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = this.then(
    composed {
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            onClick()
        }
    }
)

@OptIn(ExperimentalMaterial3Api::class)
fun dismissBottomSheet(scope: CoroutineScope, sheetState: SheetState, onDismiss: () -> Unit) {
    scope.launch {
        sheetState.hide()
    }.invokeOnCompletion {
        if (!sheetState.isVisible) {
            onDismiss()
        }
    }
}

@Composable
inline fun <T> AnimatedNullableVisibility(
    value: T?,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn() + expandVertically(),
    exit: ExitTransition = fadeOut() + shrinkVertically(),
    crossinline content: @Composable (scope: AnimatedVisibilityScope, T) -> Unit
) {
    val ref = remember {
        Ref<T>()
    }

    ref.value = value ?: ref.value

    AnimatedVisibility(
        modifier = modifier,
        visible = value != null,
        enter = enter,
        exit = exit,
        content = {
            ref.value?.let { value ->
                content(this, value)
            }
        }
    )
}

//fun String.toColor(
//    defaultColor: Color = Color.Unspecified
//): Color {
//    return try {
//        Color(this.toColorInt())
//    } catch (e: Exception) {
//        defaultColor
//    }
//}
