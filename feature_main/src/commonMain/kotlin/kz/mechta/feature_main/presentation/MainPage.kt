package kz.mechta.feature_main.presentation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import kz.mechta.feature_tab_catalog.presentation.TabCatalogPage
import kz.mechta.feature_tab_home.presentation.TabHomePage

class MainPage : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(
            TabHomePage,
        ) { tabNavigator ->
            val tabs = listOf(TabHomePage, TabCatalogPage)
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        tabs.forEach { tab ->
                            NavigationBarItem(
                                selected = tabNavigator.current == tab,
                                onClick = { tabNavigator.current = tab },
                                label = {
                                    Text(
                                        text = tab.options.title
                                    )
                                },
                                icon = {
                                    tab.options.icon?.let {
                                        Icon(
                                            painter = it,
                                            contentDescription = tab.options.title
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}