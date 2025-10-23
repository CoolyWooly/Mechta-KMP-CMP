package kz.mechta.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.core_ui.util.shimmerEffect

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    radius: Dp = 8.dp,
    shape: Shape = RoundedCornerShape(radius),
) {
    Box(
        modifier = modifier
            .background(color = MechtaTheme.colors.baseGeneric, shape = shape)
            .clip(shape)
            .shimmerEffect()
    )
}