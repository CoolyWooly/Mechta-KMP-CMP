package kz.mechta.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.ic_qr_code_scan
import kz.mechta.core_ui.generated.resources.ic_search_2
import kz.mechta.core_ui.generated.resources.search_products
import kz.mechta.core_ui.theme.MechtaTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBarButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    onClick: () -> Unit,
    onScannerClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(color = MechtaTheme.colors.baseGeneric)
            .clickable { onClick() }
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_search_2),
            contentDescription = null,
            tint = MechtaTheme.colors.textHint,
        )
        Spacer(modifier = Modifier.width(8.dp))
        if (text.isNullOrBlank()) {
            Text(
                text = stringResource(Res.string.search_products),
                style = MechtaTheme.typography.textBody3,
                color = MechtaTheme.colors.textSecondary
            )
        } else {
            Text(
                text = text,
                style = MechtaTheme.typography.textBody3,
                color = MechtaTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        CustomIconButton(
            painter = painterResource(Res.drawable.ic_qr_code_scan),
            onClick = onScannerClick,
            tint = MechtaTheme.colors.textSecondary
        )
    }
}