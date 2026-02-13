package kz.mechta.feature_main.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import kz.mechta.feature_catalog.presentation.CatalogPage
import kz.mechta.feature_home.presentation.HomePage
import kz.mechta.feature_main.presentation.Route

@Composable
internal fun TabHost(
    startDestination: Route,
) {
    val tabNavController = rememberNavController()
    NavHost(
        navController = tabNavController,
        startDestination = startDestination
    ) {
        composable<Route.HomeRoute> {
            HomePage(
                onNavigateToSearch = {},
                onNavigateToNotifications = {},
                onNavigateToCitySelection = {},
                onNavigateToUrl = {}
            )
        }
        composable<Route.CatalogRoute> {
            CatalogPage(
                navigateToSearch = {},
                navigateToQr = { },
                navigateToSubcatalog = {  },
                navigateToBrand = { _, _, _ ->  },
                navigateToDeeplink = {  }
            )
        }
        composable<Route.CartRoute> {

        }
    }
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