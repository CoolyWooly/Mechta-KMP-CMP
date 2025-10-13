package kz.mechta.feature_tab_catalog.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import kz.mechta.feature_home.presentation.HomePage

object TabCatalogPage: Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Close)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Catalog",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(HomePage())
    }
}