package kz.mechta.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Unspecified,
    tint: Color = Color.Unspecified
) {
    Icon(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false)
            ),
        painter = painter,
        contentDescription = null,
        tint = tint
    )
}

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Unspecified,
    tint: Color = Color.Unspecified,
    shape: Shape = CircleShape
) {
    Icon(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false)
            ),
        imageVector = imageVector,
        contentDescription = null,
        tint = tint
    )
}