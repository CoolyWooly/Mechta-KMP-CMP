package kz.mechta.feature_catalog.presentation

import androidx.compose.ui.input.key.Key.Companion.R
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_navigation.NavConstants.NAV_ARGS_KEY
import kz.mechta.core_navigation.nav_args.CatalogPageArgs
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.gift_cards
import kz.mechta.core_ui.generated.resources.img_gift_card
import kz.mechta.core_ui.generated.resources.img_promotions_percent
import kz.mechta.core_ui.generated.resources.promotions
import kz.mechta.feature_catalog.domain.model.CatalogModel
import kz.mechta.feature_catalog.domain.use_case.GetBrandsUseCase
import kz.mechta.feature_catalog.domain.use_case.GetCatalogUseCase
import kz.mechta.feature_catalog.presentation.util.GIFT_CARDS_LINK
import kz.mechta.feature_catalog.presentation.util.PROMOTIONS_LINK
import org.jetbrains.compose.resources.getString

internal class CatalogViewModel(
//    private val sendEventUseCase: SendEventUseCase,
    private val getCatalogUseCase: GetCatalogUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
//    private val savedStateHandle: SavedStateHandle,
//    private val stringResourcesProvider: StringResourcesProvider
) : ViewModel(), CatalogContract {

    private val mutableState = MutableStateFlow(CatalogContract.State())
    override val state: StateFlow<CatalogContract.State> = mutableState.asStateFlow()

    private val effectFlow = MutableSharedFlow<CatalogContract.Effect>()
    override val effect: SharedFlow<CatalogContract.Effect> = effectFlow.asSharedFlow()

//    private val args = savedStateHandle.get<CatalogPageArgs>(NAV_ARGS_KEY)

    init {
        initState()
        getCatalog()
        getBrands()
    }

    override fun event(event: CatalogContract.Event) {
        when (event) {
            is CatalogContract.Event.OnResume -> onResume()
            is CatalogContract.Event.OnSearchClick -> onSearchClick()
            is CatalogContract.Event.OnScannerClick -> onScannerClick()
            is CatalogContract.Event.OnCategoryClick -> onSubcatalogClick(event.slug)
            is CatalogContract.Event.OnFeatureClick -> onFeatureClick(event.url)
            is CatalogContract.Event.OnBrandClick -> onBrandClick(event.index, event.name, event.slug)
            is CatalogContract.Event.OnTabSelected -> onTabSelected(event.index)
        }
    }

    private fun initState() {
//        mutableState.update {
//            it.copy(
//                tabIndex = args?.tabIndex ?: 0,
//            )
//        }
    }

    private fun onResume() {
//        sendEventUseCase(
//            EventCatalogLookPage(page = args?.source ?: EventSource.Other)
//        )
    }

    private fun getCatalog() {
        viewModelScope.launch {
            mutableState.update { it.copy(isLoadingCatalog = true) }
            when (val data = getCatalogUseCase()) {
                is Resource.Failure -> {
                    when (data) {
                        is Resource.Failure.FailureNetwork -> { }
                        is Resource.Failure.FailureServer -> { showPopUpError(data.message) }
                    }
                }
                is Resource.Success -> {
                    mutableState.update {
                        it.copy(catalogList = data.value)
                    }
                }
            }
            mutableState.update { it.copy(isLoadingCatalog = false) }
        }
    }

    private fun getBrands() {
        viewModelScope.launch {
            mutableState.update { it.copy(isLoadingBrands = true) }
            when (val data = getBrandsUseCase()) {
                is Resource.Failure -> {
                    when (data) {
                        is Resource.Failure.FailureNetwork -> {  }
                        is Resource.Failure.FailureServer -> { showPopUpError(data.message) }
                    }
                }
                is Resource.Success -> {
                    mutableState.update {
                        it.copy(brandsList = data.value)
                    }
                }
            }
            mutableState.update { it.copy(isLoadingBrands = false) }
        }
    }

    private fun onSearchClick() {
        viewModelScope.launch {
            effectFlow.emit(CatalogContract.Effect.NavigateToSearch)
        }
    }

    private fun onScannerClick() {
        viewModelScope.launch {
            effectFlow.emit(CatalogContract.Effect.NavigateToQr)
        }
//        sendEventUseCase(
//            EventScanProductCode(page = EventSource.Catalog)
//        )
    }

    private fun onSubcatalogClick(slug: String) {
        viewModelScope.launch {
            effectFlow.emit(CatalogContract.Effect.NavigateToSubcatalog(slug))
        }
//        sendEventUseCase(
//            EventCategoryNavigation(page = EventSource.Catalog, categoryLevel = 1)
//        )
    }

    private fun onFeatureClick(url: String) {
        viewModelScope.launch {
            effectFlow.emit(CatalogContract.Effect.NavigateToDeeplink(url = url))
        }
    }

    private fun onBrandClick(index: Int, name: String, slug: String) {
        viewModelScope.launch {
            effectFlow.emit(CatalogContract.Effect.NavigateToBrand(index, name, slug))
        }
//        sendEventUseCase(
//            EventCategoryNavigation(page = EventSource.Catalog)
//        )
    }

    private fun showPopUpError(message: String?) {
        if (!message.isNullOrBlank()) {
//            val popUpData = PopUpData(
//                type = PopUpType.ERROR,
//                title = message,
//            )
//            viewModelScope.launch {
//                effectFlow.emit(CatalogContract.Effect.ShowError(popUpData))
//            }
        }
    }

    private fun onTabSelected(index: Int) {
        mutableState.update {
            it.copy(tabIndex = index)
        }
    }

}