package kz.mechta.feature_catalog.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.core_ui.utils.collectInLaunchedEffect
import kz.mechta.core_ui.utils.use
import kz.mechta.feature_catalog.presentation.components.TabBrands
import kz.mechta.feature_catalog.presentation.components.TabCatalog
import kz.mechta.feature_catalog.presentation.components.TopBar
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CatalogPage(
    navigateToSearch: () -> Unit,
    navigateToQr: () -> Unit,
    navigateToSubcatalog: (String) -> Unit,
    navigateToBrand: (Int, String, String) -> Unit,
    navigateToDeeplink: (String) -> Unit
) {
    val viewModel = koinViewModel<CatalogViewModel>()
//    val viewModel: CatalogViewModel = koinInject()

    val (state, event, effect) = use(viewModel = viewModel)

    CatalogScreen(
        uiState = state,
        effectFlow = effect,
        event = event,
        navigateToSearch = navigateToSearch,
        navigateToQr = navigateToQr,
        navigateToSubcatalog = navigateToSubcatalog,
        navigateToBrand = navigateToBrand,
        navigateToDeeplink = navigateToDeeplink
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun CatalogScreen(
    uiState: CatalogContract.State,
    effectFlow: SharedFlow<CatalogContract.Effect>,
    event: (CatalogContract.Event) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToQr: () -> Unit,
    navigateToSubcatalog: (String) -> Unit,
    navigateToBrand: (Int, String, String) -> Unit,
    navigateToDeeplink: (String) -> Unit
) {

//    val popUpState = rememberPopUpState()

    effectFlow.collectInLaunchedEffect {
        when (it) {
            is CatalogContract.Effect.NavigateToSearch -> navigateToSearch()
            is CatalogContract.Effect.NavigateToQr -> navigateToQr()
            is CatalogContract.Effect.NavigateToSubcatalog -> navigateToSubcatalog(it.slug)
            is CatalogContract.Effect.NavigateToDeeplink -> navigateToDeeplink(it.url)
            is CatalogContract.Effect.NavigateToBrand -> navigateToBrand(it.index, it.name, it.slug)
//            is CatalogContract.Effect.ShowError -> popUpState.show(it.popUpData)
        }
    }

    val pagerState = rememberPagerState(initialPage = uiState.tabIndex) { 2 }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.tabIndex) {
        coroutineScope.launch { pagerState.animateScrollToPage(uiState.tabIndex) }
    }

    LaunchedEffect(Unit) {
        event.invoke(CatalogContract.Event.OnResume)
    }

//    PopUpContainer(
//        state = popUpState,
//    ) {
        Scaffold(
            containerColor = MechtaTheme.colors.baseGenericSolid,
            topBar = {
                TopBar(
                    tabIndex = pagerState.currentPage,
                    onTabSelected = { index ->
                        event(CatalogContract.Event.OnTabSelected(index))
                    },
                    onSearchClick = { event(CatalogContract.Event.OnSearchClick) },
                    onScannerClick = { event(CatalogContract.Event.OnScannerClick) }
                )
            },
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->

                if (page == 0) {
                    TabCatalog(
                        modifier = Modifier.padding(it),
                        isLoading = uiState.isLoadingCatalog,
                        catalogList = uiState.catalogList,
                        onCategoryClick = { event(CatalogContract.Event.OnCategoryClick(it)) },
                        onFeatureClick = { event(CatalogContract.Event.OnFeatureClick(it)) }
                    )
                } else {
                    TabBrands (
                        modifier = Modifier.padding(it),
                        isLoading = uiState.isLoadingBrands,
                        brandsList = uiState.brandsList,
                        onBrandClick = { index, name, slug ->
                            event(CatalogContract.Event.OnBrandClick(index, name, slug))
                        }
                    )
                }
            }
        }
    }
//}