package kz.mechta.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.core_ui.util.noRippleClickable

@Composable
fun CustomTabView(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    tabIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    TabRow(
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        selectedTabIndex = tabIndex,
        containerColor = MechtaTheme.colors.baseGeneric,
        indicator = { tabPositions ->
            if (tabIndex < tabPositions.size) {
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .padding(2.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(6.dp))
                        .background(MechtaTheme.colors.brandBaseBackground)
                        .zIndex(1f)
                )
            }
        },
        divider = { }
    ) {
        tabs.forEachIndexed { index, title ->
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .zIndex(2f)
                    .noRippleClickable { onTabSelected(index) }
                    .padding(vertical = 7.dp),
                text = title,
                color = MechtaTheme.colors.textPrimary,
                style = MechtaTheme.typography.textBody1,
                textAlign = TextAlign.Center
            )
        }
    }
}