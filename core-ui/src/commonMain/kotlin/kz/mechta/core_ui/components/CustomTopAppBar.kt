package kz.mechta.core_ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.ic_arrow_left
import kz.mechta.core_ui.theme.MechtaTheme
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    text: String,
    onBackClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MechtaTheme.colors.brandBaseBackground
        ),
        title = {
            Text(
                text = text,
                style = MechtaTheme.typography.headerHeader1,
                color = MechtaTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_left),
                    contentDescription = null,
                    tint = MechtaTheme.colors.brandTextBrand
                )
            }
        },
        actions = actions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    text: String,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MechtaTheme.colors.brandBaseBackground
        ),
        title = {
            Text(
                text = text,
                style = MechtaTheme.typography.headerHeader1,
                color = MechtaTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        actions = actions,
    )
}