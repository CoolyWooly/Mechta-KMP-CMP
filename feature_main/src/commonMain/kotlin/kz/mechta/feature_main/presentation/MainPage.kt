package kz.mechta.feature_main.presentation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import kz.mechta.feature_home.presentation.HomePage
import kz.mechta.feature_tab_catalog.presentation.TabCatalogPage
import mechtakmp.feature_main.generated.resources.Res
import org.koin.compose.viewmodel.koinViewModel

private inline fun <reified T : Any> NavDestination?.isOnRoute(): Boolean {
    return this?.hierarchy?.any { it.hasRoute<T>() } == true
}

    @Composable
fun MainPage() {
    val rootNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentBackStack by rootNavController.currentBackStackEntryAsState()
                val currentRootDestination = currentBackStack?.destination
                TabsList.values().forEach { tab ->
                    val selected = when (tab.tab) {
                        AppTab.TabHome    -> currentRootDestination.isOnRoute<AppTab.TabHome>()
                        AppTab.TabCatalog -> currentRootDestination.isOnRoute<AppTab.TabCatalog>()
                        AppTab.TabCart    -> currentRootDestination.isOnRoute<AppTab.TabCart>()
                    }

                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = null
                            )
                        },
                        label = { Text(tab.title) },
                        selected = selected,
                        onClick = {
                            // При нажатии на вкладку выполняем навигацию:
                            rootNavController.navigate(tab.tab) {
                                // Настройки NavOptions для поддержки нескольких бэкстеков:
                                popUpTo(rootNavController.graph.findStartDestination().id) {
                                    saveState = true  // Сохранить состояние текущего графа
                                }
                                launchSingleTop = true  // Не создавать дубликатов в бэкстеке
                                restoreState = true     // Восстановить сохранённый состояние при возврате на вкладку
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = rootNavController,
            startDestination = AppTab.TabHome
        ) {
            composable<AppTab.TabHome> {
                TabHost(startDestination = Route.HomeRoute)
            }
            composable<AppTab.TabCatalog> {
                TabHost(startDestination = Route.CatalogRoute)
            }
            composable<AppTab.TabCart> {
                TabHost(startDestination = Route.CartRoute)
            }
        }
    }
}


internal enum class TabsList(val tab: AppTab, val title: String) {
    TabHome(AppTab.TabHome, "TabHome"),
    TabCatalog(AppTab.TabCatalog, "TabCatalog"),
    TabCart(AppTab.TabCart, "TabCart")
}

@Serializable
internal sealed class AppTab {

    @Serializable
    data object TabHome : AppTab()

    @Serializable
    data object TabCatalog : AppTab()

    @Serializable
    data object TabCart : AppTab()

}

@Serializable
internal sealed class Route {

    @Serializable
    data object HomeRoute : Route()

    @Serializable
    data object CatalogRoute : Route()

    @Serializable
    data object CartRoute : Route()

}

@Composable
private fun TabHost(
    startDestination: Route,
) {
    val tabNavController = rememberNavController()
    NavHost(
        navController = tabNavController,
        startDestination = startDestination
    ) {
        composable<Route.HomeRoute> {
            HomePage("HomeRoute", onClick = {
                tabNavController.navigate(Route.CatalogRoute)
            })
        }
        composable<Route.CatalogRoute> {
            HomePage("CatalogRoute", onClick = {
                tabNavController.navigate(Route.HomeRoute)
            })
        }
        composable<Route.CartRoute> {
            HomePage("CartRoute", onClick = {
                tabNavController.navigate(Route.HomeRoute)
            })
        }
    }
}