package kz.mechta.feature_catalog.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.mechta.core_ui.components.CustomTabView
import kz.mechta.core_ui.components.SearchBarButton
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.brands
import kz.mechta.core_ui.generated.resources.catalog
import kz.mechta.core_ui.theme.MechtaTheme
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    tabIndex: Int,
    onSearchClick: () -> Unit,
    onScannerClick: () -> Unit,
    onTabSelected: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MechtaTheme.colors.brandBaseBackground)
    ) {
        SearchBarButton(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            onClick = onSearchClick,
            onScannerClick = onScannerClick,
        )

        CustomTabView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            tabs = listOf(stringResource(Res.string.catalog), stringResource(Res.string.brands)),
            tabIndex = tabIndex,
            onTabSelected = onTabSelected
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = MechtaTheme.colors.lineGeneric,
            thickness = 0.5.dp
        )
    }
}