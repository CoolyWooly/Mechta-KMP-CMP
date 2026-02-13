package kz.mechta.feature_home.presentation

import kz.mechta.core_ui.utils.UnidirectionalViewModel
import kz.mechta.feature_home.domain.model.BannerModel
import kz.mechta.feature_home.domain.model.BrandModel
import kz.mechta.feature_home.domain.model.CategoryModel
import kz.mechta.feature_home.domain.model.NewsModel
import kz.mechta.feature_home.domain.model.ProductModel
import kz.mechta.feature_home.domain.model.PublicationModel
import kz.mechta.feature_home.domain.model.SocialModel
import kz.mechta.feature_home.domain.model.TopCategoryModel

internal interface HomeContract :
    UnidirectionalViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect> {

    data class State(
        val isLoading: Boolean = true,
        val isRefreshing: Boolean = false,
        val banners: List<BannerModel> = emptyList(),
        val categories: List<CategoryModel> = emptyList(),
        val brands: List<BrandModel> = emptyList(),
        val publications: List<PublicationModel> = emptyList(),
        val news: List<NewsModel> = emptyList(),
        val topCategories: List<TopCategoryModel> = emptyList(),
        val socials: List<SocialModel> = emptyList(),
        val currentBannerIndex: Int = 0,
        val cityName: String = "",
        val subscribeEmail: String = "",
        val isSubscribing: Boolean = false,
        val subscribeSuccess: Boolean = false
    )

    sealed class Event {
        data object OnRefresh : Event()
        data object OnRetry : Event()
        data class OnBannerClick(val banner: BannerModel) : Event()
        data class OnCategoryClick(val category: CategoryModel) : Event()
        data class OnBrandClick(val brand: BrandModel) : Event()
        data class OnPublicationClick(val publication: PublicationModel) : Event()
        data class OnNewsClick(val news: NewsModel) : Event()
        data class OnProductClick(val product: ProductModel) : Event()
        data class OnTopCategoryClick(val topCategory: TopCategoryModel) : Event()
        data class OnSocialClick(val social: SocialModel) : Event()
        data object OnSearchClick : Event()
        data object OnNotificationsClick : Event()
        data object OnCityClick : Event()
        data class OnBannerPageChanged(val index: Int) : Event()
        data class OnEmailChanged(val email: String) : Event()
        data object OnSubscribeClick : Event()
    }

    sealed class Effect {
        data class NavigateToUrl(val url: String) : Effect()
        data class OpenExternalUrl(val url: String) : Effect()
        data object NavigateToSearch : Effect()
        data object NavigateToNotifications : Effect()
        data object NavigateToCitySelection : Effect()
        data class ShowError(val message: String) : Effect()
        data object ShowSubscribeSuccess : Effect()
    }
}
