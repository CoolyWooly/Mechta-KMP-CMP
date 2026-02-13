package kz.mechta.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.mechta.core_data.domain.model.Resource
import kz.mechta.core_data.domain.use_case.city.GetCityUseCase
import kz.mechta.feature_home.domain.model.BannerModel
import kz.mechta.feature_home.domain.model.BrandModel
import kz.mechta.feature_home.domain.model.CategoryModel
import kz.mechta.feature_home.domain.model.NewsModel
import kz.mechta.feature_home.domain.model.ProductModel
import kz.mechta.feature_home.domain.model.PublicationModel
import kz.mechta.feature_home.domain.model.SocialModel
import kz.mechta.feature_home.domain.model.TopCategoryModel
import kz.mechta.feature_home.domain.use_case.GetBannersUseCase
import kz.mechta.feature_home.domain.use_case.GetBrandsUseCase
import kz.mechta.feature_home.domain.use_case.GetCategoriesUseCase
import kz.mechta.feature_home.domain.use_case.GetNewsUseCase
import kz.mechta.feature_home.domain.use_case.GetPublicationsUseCase
import kz.mechta.feature_home.domain.use_case.GetSocialsUseCase
import kz.mechta.feature_home.domain.use_case.GetTopCategoriesUseCase
import kz.mechta.feature_home.domain.use_case.SubscribeEmailUseCase

internal class HomeViewModel(
    private val getBannersUseCase: GetBannersUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getPublicationsUseCase: GetPublicationsUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val getTopCategoriesUseCase: GetTopCategoriesUseCase,
    private val getSocialsUseCase: GetSocialsUseCase,
    private val subscribeEmailUseCase: SubscribeEmailUseCase,
    private val getCityUseCase: GetCityUseCase
) : ViewModel(), HomeContract {

    private val mutableState = MutableStateFlow(HomeContract.State())
    override val state: StateFlow<HomeContract.State> = mutableState.asStateFlow()

    private val effectFlow = MutableSharedFlow<HomeContract.Effect>()
    override val effect: SharedFlow<HomeContract.Effect> = effectFlow.asSharedFlow()

    init {
        loadData()
    }

    override fun event(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnRefresh -> refresh()
            is HomeContract.Event.OnRetry -> loadData()
            is HomeContract.Event.OnBannerClick -> onBannerClick(event.banner)
            is HomeContract.Event.OnCategoryClick -> onCategoryClick(event.category)
            is HomeContract.Event.OnBrandClick -> onBrandClick(event.brand)
            is HomeContract.Event.OnPublicationClick -> onPublicationClick(event.publication)
            is HomeContract.Event.OnNewsClick -> onNewsClick(event.news)
            is HomeContract.Event.OnProductClick -> onProductClick(event.product)
            is HomeContract.Event.OnTopCategoryClick -> onTopCategoryClick(event.topCategory)
            is HomeContract.Event.OnSocialClick -> onSocialClick(event.social)
            is HomeContract.Event.OnSearchClick -> onSearchClick()
            is HomeContract.Event.OnNotificationsClick -> onNotificationsClick()
            is HomeContract.Event.OnCityClick -> onCityClick()
            is HomeContract.Event.OnBannerPageChanged -> onBannerPageChanged(event.index)
            is HomeContract.Event.OnEmailChanged -> onEmailChanged(event.email)
            is HomeContract.Event.OnSubscribeClick -> onSubscribeClick()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            mutableState.update { it.copy(isLoading = true) }

            val cityName = getCityUseCase()?.name.orEmpty()

            val bannersDeferred = async { getBannersUseCase() }
            val categoriesDeferred = async { getCategoriesUseCase() }
            val brandsDeferred = async { getBrandsUseCase() }
            val publicationsDeferred = async { getPublicationsUseCase() }
            val newsDeferred = async { getNewsUseCase() }
            val topCategoriesDeferred = async { getTopCategoriesUseCase() }
            val socialsDeferred = async { getSocialsUseCase() }

            val bannersResult = bannersDeferred.await()
            val categoriesResult = categoriesDeferred.await()
            val brandsResult = brandsDeferred.await()
            val publicationsResult = publicationsDeferred.await()
            val newsResult = newsDeferred.await()
            val topCategoriesResult = topCategoriesDeferred.await()
            val socialsResult = socialsDeferred.await()

            mutableState.update { state ->
                state.copy(
                    isLoading = false,
                    cityName = cityName,
                    banners = (bannersResult as? Resource.Success)?.value.orEmpty(),
                    categories = (categoriesResult as? Resource.Success)?.value.orEmpty(),
                    brands = (brandsResult as? Resource.Success)?.value.orEmpty(),
                    publications = (publicationsResult as? Resource.Success)?.value.orEmpty(),
                    news = (newsResult as? Resource.Success)?.value.orEmpty(),
                    topCategories = (topCategoriesResult as? Resource.Success)?.value.orEmpty(),
                    socials = (socialsResult as? Resource.Success)?.value.orEmpty()
                )
            }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            mutableState.update { it.copy(isRefreshing = true) }

            val cityName = getCityUseCase()?.name.orEmpty()

            val bannersDeferred = async { getBannersUseCase() }
            val categoriesDeferred = async { getCategoriesUseCase() }
            val brandsDeferred = async { getBrandsUseCase() }
            val publicationsDeferred = async { getPublicationsUseCase() }
            val newsDeferred = async { getNewsUseCase() }
            val topCategoriesDeferred = async { getTopCategoriesUseCase() }
            val socialsDeferred = async { getSocialsUseCase() }

            val bannersResult = bannersDeferred.await()
            val categoriesResult = categoriesDeferred.await()
            val brandsResult = brandsDeferred.await()
            val publicationsResult = publicationsDeferred.await()
            val newsResult = newsDeferred.await()
            val topCategoriesResult = topCategoriesDeferred.await()
            val socialsResult = socialsDeferred.await()

            mutableState.update { state ->
                state.copy(
                    isRefreshing = false,
                    cityName = cityName,
                    banners = (bannersResult as? Resource.Success)?.value ?: state.banners,
                    categories = (categoriesResult as? Resource.Success)?.value ?: state.categories,
                    brands = (brandsResult as? Resource.Success)?.value ?: state.brands,
                    publications = (publicationsResult as? Resource.Success)?.value ?: state.publications,
                    news = (newsResult as? Resource.Success)?.value ?: state.news,
                    topCategories = (topCategoriesResult as? Resource.Success)?.value ?: state.topCategories,
                    socials = (socialsResult as? Resource.Success)?.value ?: state.socials
                )
            }
        }
    }

    private fun onBannerClick(banner: BannerModel) {
        viewModelScope.launch {
            if (banner.url.isNotEmpty()) {
                effectFlow.emit(HomeContract.Effect.NavigateToUrl(banner.url))
            }
        }
    }

    private fun onCategoryClick(category: CategoryModel) {
        viewModelScope.launch {
            if (category.url.isNotEmpty()) {
                effectFlow.emit(HomeContract.Effect.NavigateToUrl(category.url))
            }
        }
    }

    private fun onBrandClick(brand: BrandModel) {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.NavigateToUrl(brand.slug))
        }
    }

    private fun onPublicationClick(publication: PublicationModel) {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.NavigateToUrl(publication.slug))
        }
    }

    private fun onNewsClick(news: NewsModel) {
        viewModelScope.launch {
            val url = news.linkUrl ?: return@launch
            if (news.isExternalLink) {
                effectFlow.emit(HomeContract.Effect.OpenExternalUrl(url))
            } else {
                effectFlow.emit(HomeContract.Effect.NavigateToUrl(url))
            }
        }
    }

    private fun onProductClick(product: ProductModel) {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.NavigateToUrl(product.slug))
        }
    }

    private fun onTopCategoryClick(topCategory: TopCategoryModel) {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.NavigateToUrl(topCategory.categorySlug))
        }
    }

    private fun onSocialClick(social: SocialModel) {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.OpenExternalUrl(social.url))
        }
    }

    private fun onSearchClick() {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.NavigateToSearch)
        }
    }

    private fun onNotificationsClick() {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.NavigateToNotifications)
        }
    }

    private fun onCityClick() {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.NavigateToCitySelection)
        }
    }

    private fun onBannerPageChanged(index: Int) {
        mutableState.update { it.copy(currentBannerIndex = index) }
    }

    private fun onEmailChanged(email: String) {
        mutableState.update { it.copy(subscribeEmail = email, subscribeSuccess = false) }
    }

    private fun onSubscribeClick() {
        val email = state.value.subscribeEmail
        if (email.isBlank()) return

        viewModelScope.launch {
            mutableState.update { it.copy(isSubscribing = true) }

            when (val result = subscribeEmailUseCase(email)) {
                is Resource.Success -> {
                    mutableState.update {
                        it.copy(
                            isSubscribing = false,
                            subscribeSuccess = true,
                            subscribeEmail = ""
                        )
                    }
                    effectFlow.emit(HomeContract.Effect.ShowSubscribeSuccess)
                }
                is Resource.Failure -> {
                    mutableState.update { it.copy(isSubscribing = false) }
                    val message = (result as? Resource.Failure.FailureServer)?.message
                        ?: "Ошибка подписки"
                    effectFlow.emit(HomeContract.Effect.ShowError(message))
                }
            }
        }
    }
}
