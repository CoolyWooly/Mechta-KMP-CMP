package kz.mechta.feature_main.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.RowScope
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
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.cart
import kz.mechta.core_ui.generated.resources.catalog
import kz.mechta.core_ui.generated.resources.favourites
import kz.mechta.core_ui.generated.resources.home
import kz.mechta.core_ui.generated.resources.ic_nav_1
import kz.mechta.core_ui.generated.resources.ic_nav_2
import kz.mechta.core_ui.generated.resources.ic_nav_3
import kz.mechta.core_ui.generated.resources.ic_nav_4
import kz.mechta.core_ui.generated.resources.ic_nav_5
import kz.mechta.core_ui.generated.resources.profile
import kz.mechta.feature_home.presentation.HomePage
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

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
                        TabRoute.TabHome -> currentRootDestination.isOnRoute<TabRoute.TabHome>()
                        TabRoute.TabCatalog -> currentRootDestination.isOnRoute<TabRoute.TabCatalog>()
                        TabRoute.TabCart -> currentRootDestination.isOnRoute<TabRoute.TabCart>()
                        TabRoute.TabFavorites -> currentRootDestination.isOnRoute<TabRoute.TabFavorites>()
                        TabRoute.TabProfile -> currentRootDestination.isOnRoute<TabRoute.TabProfile>()
                    }
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(tab.res),
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(tab.title)) },
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
            startDestination = TabRoute.TabHome,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            composable<TabRoute.TabHome> {
                TabHost(startDestination = Route.HomeRoute)
            }
            composable<TabRoute.TabCatalog> {
                TabHost(startDestination = Route.CatalogRoute)
            }
            composable<TabRoute.TabCart> {
                TabHost(startDestination = Route.CartRoute)
            }
            composable<TabRoute.TabFavorites> {
                TabHost(startDestination = Route.CartRoute)
            }
            composable<TabRoute.TabProfile> {
                TabHost(startDestination = Route.CartRoute)
            }
        }
    }
}

private enum class TabsList(val tab: TabRoute, val title: StringResource, val res: DrawableResource) {
    TabHome(TabRoute.TabHome, Res.string.home, Res.drawable.ic_nav_1),
    TabCatalog(TabRoute.TabCatalog, Res.string.catalog, Res.drawable.ic_nav_2),
    TabCart(TabRoute.TabCart, Res.string.cart, Res.drawable.ic_nav_3),
    TabFavorites(TabRoute.TabFavorites, Res.string.favourites, Res.drawable.ic_nav_4),
    TabProfile(TabRoute.TabProfile, Res.string.profile, Res.drawable.ic_nav_5),
}

@Serializable
private sealed class TabRoute {

    @Serializable
    data object TabHome : TabRoute()

    @Serializable
    data object TabCatalog : TabRoute()

    @Serializable
    data object TabCart : TabRoute()

    @Serializable
    data object TabFavorites : TabRoute()

    @Serializable
    data object TabProfile : TabRoute()
}


private inline fun <reified T : Any> NavDestination?.isOnRoute(): Boolean {
    return this?.hierarchy?.any { it.hasRoute<T>() } == true
}