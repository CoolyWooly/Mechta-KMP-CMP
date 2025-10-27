package kz.mechta.feature_catalog.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.use_case.city.GetCityUseCase
import kz.mechta.core_navigation.NavConstants.NAV_ARGS_KEY
import kz.mechta.core_navigation.nav_args.CatalogPageArgs
import kz.mechta.feature_catalog.domain.use_case.GetBrandsUseCase
import kz.mechta.feature_catalog.domain.use_case.GetCatalogUseCase

internal class CatalogViewModel(
//    private val sendEventUseCase: SendEventUseCase,
    private val getCatalogUseCase: GetCatalogUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getCityUseCase: GetCityUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(), CatalogContract {

    private val mutableState = MutableStateFlow(CatalogContract.State())
    override val state: StateFlow<CatalogContract.State> = mutableState.asStateFlow()

    private val effectFlow = MutableSharedFlow<CatalogContract.Effect>()
    override val effect: SharedFlow<CatalogContract.Effect> = effectFlow.asSharedFlow()

    private val args = savedStateHandle.get<CatalogPageArgs>(NAV_ARGS_KEY)

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
        mutableState.update {
            it.copy(
                tabIndex = args?.tabIndex ?: 0,
            )
        }
        viewModelScope.launch {
            val city = getCityUseCase()
            mutableState.update {
                it.copy(
                    test = city?.name.orEmpty(),
                )
            }
        }
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