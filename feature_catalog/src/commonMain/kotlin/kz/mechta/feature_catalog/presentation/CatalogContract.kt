package kz.mechta.feature_catalog.presentation

import kz.mechta.core_ui.utils.UnidirectionalViewModel
import kz.mechta.feature_catalog.domain.model.BrandModel
import kz.mechta.feature_catalog.domain.model.CatalogModel

internal interface CatalogContract :
    UnidirectionalViewModel<CatalogContract.State, CatalogContract.Event, CatalogContract.Effect> {

    data class State(
        val isLoadingCatalog: Boolean = false,
        val isLoadingBrands: Boolean = false,
        val catalogList: List<CatalogModel> = emptyList(),
        val brandsList: List<BrandModel> = emptyList(),
        val tabIndex: Int = 0
    )

    sealed class Event {
        data object OnResume : Event()
        data object OnSearchClick : Event()
        data object OnScannerClick : Event()
        data class OnCategoryClick(val slug: String) : Event()
        data class OnFeatureClick(val url: String) : Event()
        data class OnBrandClick(val index: Int, val name: String, val slug: String) : Event()
        data class OnTabSelected(val index: Int) : Event()
    }

    sealed class Effect {
        data object NavigateToSearch : Effect()
        data object NavigateToQr : Effect()
        data class NavigateToSubcatalog(val slug: String) : Effect()
        data class NavigateToDeeplink(val url: String) : Effect()
        data class NavigateToBrand(val index: Int, val name: String, val slug: String) : Effect()
//        data class ShowError(val popUpData: PopUpData) : Effect()
    }
}