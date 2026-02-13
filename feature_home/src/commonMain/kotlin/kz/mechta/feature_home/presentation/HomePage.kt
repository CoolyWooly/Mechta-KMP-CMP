package kz.mechta.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.mechta.core_ui.components.CustomImageView
import kz.mechta.core_ui.components.SearchBarButton
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.actions
import kz.mechta.core_ui.generated.resources.home
import kz.mechta.core_ui.generated.resources.ic_arrow_down
import kz.mechta.core_ui.generated.resources.ic_notification_home
import kz.mechta.core_ui.generated.resources.news
import kz.mechta.core_ui.generated.resources.popular_brands
import kz.mechta.core_ui.generated.resources.popular_categories
import kz.mechta.core_ui.generated.resources.subscribed
import kz.mechta.core_ui.generated.resources.to_see_all
import kz.mechta.core_ui.generated.resources.top_selling_items
import kz.mechta.core_ui.generated.resources.x_tg
import kz.mechta.core_ui.generated.resources.your_email
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.core_ui.utils.collectInLaunchedEffect
import kz.mechta.core_ui.utils.use
import kz.mechta.feature_home.domain.model.BannerModel
import kz.mechta.feature_home.domain.model.BrandModel
import kz.mechta.feature_home.domain.model.CategoryModel
import kz.mechta.feature_home.domain.model.NewsModel
import kz.mechta.feature_home.domain.model.ProductModel
import kz.mechta.feature_home.domain.model.PublicationModel
import kz.mechta.feature_home.domain.model.SocialModel
import kz.mechta.feature_home.domain.model.TopCategoryModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    onNavigateToSearch: () -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
    onNavigateToCitySelection: () -> Unit = {},
    onNavigateToUrl: (String) -> Unit = {}
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val (state, event, effect) = use(viewModel = viewModel)

    effect.collectInLaunchedEffect { eff ->
        when (eff) {
            is HomeContract.Effect.NavigateToUrl -> onNavigateToUrl(eff.url)
            is HomeContract.Effect.OpenExternalUrl -> onNavigateToUrl(eff.url)
            is HomeContract.Effect.NavigateToSearch -> onNavigateToSearch()
            is HomeContract.Effect.NavigateToNotifications -> onNavigateToNotifications()
            is HomeContract.Effect.NavigateToCitySelection -> onNavigateToCitySelection()
            is HomeContract.Effect.ShowError -> {}
            is HomeContract.Effect.ShowSubscribeSuccess -> {}
        }
    }

    Scaffold(
        containerColor = MechtaTheme.colors.brandBaseBackground,
        topBar = {
            HomeTopBar(
                cityName = state.cityName,
                onCityClick = { event(HomeContract.Event.OnCityClick) },
                onNotificationsClick = { event(HomeContract.Event.OnNotificationsClick) }
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MechtaTheme.colors.brandBaseBrand)
            }
        } else {
            PullToRefreshBox(
                isRefreshing = state.isRefreshing,
                onRefresh = { event(HomeContract.Event.OnRefresh) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    // Search bar
                    SearchBarButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        onClick = { event(HomeContract.Event.OnSearchClick) },
                        onScannerClick = { event(HomeContract.Event.OnSearchClick) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Banners
                    if (state.banners.isNotEmpty()) {
                        BannersSection(
                            banners = state.banners,
                            currentIndex = state.currentBannerIndex,
                            onBannerClick = { event(HomeContract.Event.OnBannerClick(it)) },
                            onPageChanged = { event(HomeContract.Event.OnBannerPageChanged(it)) }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Categories
                    if (state.categories.isNotEmpty()) {
                        SectionHeader(
                            title = stringResource(Res.string.popular_categories),
                            onSeeAllClick = null
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        CategoriesSection(
                            categories = state.categories,
                            onCategoryClick = { event(HomeContract.Event.OnCategoryClick(it)) }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Top Categories with Products
                    state.topCategories.forEach { topCategory ->
                        SectionHeader(
                            title = topCategory.categoryName,
                            onSeeAllClick = { event(HomeContract.Event.OnTopCategoryClick(topCategory)) }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        ProductsSection(
                            products = topCategory.products,
                            onProductClick = { event(HomeContract.Event.OnProductClick(it)) }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Publications/Promotions
                    if (state.publications.isNotEmpty()) {
                        SectionHeader(
                            title = stringResource(Res.string.actions),
                            onSeeAllClick = {}
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        PublicationsSection(
                            publications = state.publications,
                            onPublicationClick = { event(HomeContract.Event.OnPublicationClick(it)) }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // News
                    if (state.news.isNotEmpty()) {
                        SectionHeader(
                            title = stringResource(Res.string.news),
                            onSeeAllClick = {}
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        NewsSection(
                            news = state.news,
                            onNewsClick = { event(HomeContract.Event.OnNewsClick(it)) }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Brands
                    if (state.brands.isNotEmpty()) {
                        SectionHeader(
                            title = stringResource(Res.string.popular_brands),
                            onSeeAllClick = null
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        BrandsSection(
                            brands = state.brands,
                            onBrandClick = { event(HomeContract.Event.OnBrandClick(it)) }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Email Subscription
                    EmailSubscriptionSection(
                        email = state.subscribeEmail,
                        isLoading = state.isSubscribing,
                        isSuccess = state.subscribeSuccess,
                        onEmailChanged = { event(HomeContract.Event.OnEmailChanged(it)) },
                        onSubscribeClick = { event(HomeContract.Event.OnSubscribeClick) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Socials
                    if (state.socials.isNotEmpty()) {
                        SocialsSection(
                            socials = state.socials,
                            onSocialClick = { event(HomeContract.Event.OnSocialClick(it)) }
                        )
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(
    cityName: String,
    onCityClick: () -> Unit,
    onNotificationsClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MechtaTheme.colors.brandBaseBackground
        ),
        title = {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onCityClick)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cityName.ifEmpty { stringResource(Res.string.home) },
                    style = MechtaTheme.typography.headerSubheader2,
                    color = MechtaTheme.colors.textPrimary
                )
                if (cityName.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_down),
                        contentDescription = null,
                        tint = MechtaTheme.colors.textSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onNotificationsClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_notification_home),
                    contentDescription = null,
                    tint = MechtaTheme.colors.textPrimary
                )
            }
        }
    )
}

@Composable
private fun BannersSection(
    banners: List<BannerModel>,
    currentIndex: Int,
    onBannerClick: (BannerModel) -> Unit,
    onPageChanged: (Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = currentIndex,
        pageCount = { banners.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        onPageChanged(pagerState.currentPage)
    }

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            pageSpacing = 12.dp
        ) { page ->
            val banner = banners[page]
            CustomImageView(
                model = banner.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onBannerClick(banner) },
                contentScale = ContentScale.Crop
            )
        }

        if (banners.size > 1) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                banners.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(if (index == pagerState.currentPage) 8.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == pagerState.currentPage)
                                    MechtaTheme.colors.brandBaseBrand
                                else
                                    MechtaTheme.colors.baseGenericMedium
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    onSeeAllClick: (() -> Unit)?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MechtaTheme.typography.headerHeader1,
            color = MechtaTheme.colors.textPrimary
        )
        if (onSeeAllClick != null) {
            Text(
                text = stringResource(Res.string.to_see_all),
                style = MechtaTheme.typography.textBody1,
                color = MechtaTheme.colors.brandTextBrand,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(onClick = onSeeAllClick)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
private fun CategoriesSection(
    categories: List<CategoryModel>,
    onCategoryClick: (CategoryModel) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = categories,
            key = { it.url }
        ) { category ->
            CategoryItem(
                category = category,
                onClick = { onCategoryClick(category) }
            )
        }
    }
}

@Composable
private fun CategoryItem(
    category: CategoryModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomImageView(
            model = category.image,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MechtaTheme.colors.baseGeneric),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.title,
            style = MechtaTheme.typography.textCaption2,
            color = MechtaTheme.colors.textPrimary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ProductsSection(
    products: List<ProductModel>,
    onProductClick: (ProductModel) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = products.take(10),
            key = { it.id }
        ) { product ->
            ProductItem(
                product = product,
                onClick = { onProductClick(product) }
            )
        }
    }
}

@Composable
private fun ProductItem(
    product: ProductModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MechtaTheme.colors.baseGenericSolid)
            .clickable(onClick = onClick)
    ) {
        CustomImageView(
            model = product.preview,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(MechtaTheme.colors.baseGeneric),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = product.name,
                style = MechtaTheme.typography.textCaption2,
                color = MechtaTheme.colors.textPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            product.price?.let { price ->
                Text(
                    text = stringResource(Res.string.x_tg, price.toString()),
                    style = MechtaTheme.typography.headerSubheader2,
                    color = MechtaTheme.colors.textPrimary
                )
            }
            product.oldPrice?.let { oldPrice ->
                if (oldPrice > (product.price ?: 0)) {
                    Text(
                        text = stringResource(Res.string.x_tg, oldPrice.toString()),
                        style = MechtaTheme.typography.textCaption2,
                        color = MechtaTheme.colors.textSecondary,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }
        }
    }
}

@Composable
private fun PublicationsSection(
    publications: List<PublicationModel>,
    onPublicationClick: (PublicationModel) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = publications.take(10),
            key = { it.slug }
        ) { publication ->
            PublicationItem(
                publication = publication,
                onClick = { onPublicationClick(publication) }
            )
        }
    }
}

@Composable
private fun PublicationItem(
    publication: PublicationModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MechtaTheme.colors.baseGenericSolid)
            .clickable(onClick = onClick)
    ) {
        CustomImageView(
            model = publication.image,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = publication.title,
                style = MechtaTheme.typography.headerSubheader1,
                color = MechtaTheme.colors.textPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            if (publication.previewText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = publication.previewText,
                    style = MechtaTheme.typography.textCaption2,
                    color = MechtaTheme.colors.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun NewsSection(
    news: List<NewsModel>,
    onNewsClick: (NewsModel) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = news.take(10),
            key = { it.id }
        ) { newsItem ->
            NewsItem(
                news = newsItem,
                onClick = { onNewsClick(newsItem) }
            )
        }
    }
}

@Composable
private fun NewsItem(
    news: NewsModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MechtaTheme.colors.baseGenericSolid)
            .clickable(onClick = onClick)
    ) {
        CustomImageView(
            model = news.imageSrc,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = news.name,
                style = MechtaTheme.typography.headerSubheader1,
                color = MechtaTheme.colors.textPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            if (news.previewText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = news.previewText,
                    style = MechtaTheme.typography.textCaption2,
                    color = MechtaTheme.colors.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun BrandsSection(
    brands: List<BrandModel>,
    onBrandClick: (BrandModel) -> Unit
) {
    val isDark = isSystemInDarkTheme()

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = brands,
            key = { it.slug }
        ) { brand ->
            BrandItem(
                brand = brand,
                isDark = isDark,
                onClick = { onBrandClick(brand) }
            )
        }
    }
}

@Composable
private fun BrandItem(
    brand: BrandModel,
    isDark: Boolean,
    onClick: () -> Unit
) {
    val imageUrl = if (isDark) brand.imageDark else brand.imageLight

    Box(
        modifier = Modifier
            .size(100.dp, 60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MechtaTheme.colors.baseGeneric)
            .clickable(onClick = onClick)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        CustomImageView(
            model = imageUrl.ifEmpty { brand.imageLight },
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun EmailSubscriptionSection(
    email: String,
    isLoading: Boolean,
    isSuccess: Boolean,
    onEmailChanged: (String) -> Unit,
    onSubscribeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MechtaTheme.colors.brandBaseSelection)
            .padding(16.dp)
    ) {
        Text(
            text = "Узнавайте о специальных предложениях первыми!",
            style = MechtaTheme.typography.headerSubheader2,
            color = MechtaTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Оставьте свой e-mail, чтобы сделать будущие покупки выгодней!",
            style = MechtaTheme.typography.textCaption2,
            color = MechtaTheme.colors.textSecondary
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (isSuccess) {
            Text(
                text = stringResource(Res.string.subscribed),
                style = MechtaTheme.typography.headerSubheader2,
                color = MechtaTheme.colors.textPositive
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MechtaTheme.colors.brandBaseBackground)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    if (email.isEmpty()) {
                        Text(
                            text = stringResource(Res.string.your_email),
                            style = MechtaTheme.typography.textBody2,
                            color = MechtaTheme.colors.textHint
                        )
                    }
                    BasicTextField(
                        value = email,
                        onValueChange = onEmailChanged,
                        singleLine = true,
                        textStyle = MechtaTheme.typography.textBody2.copy(
                            color = MechtaTheme.colors.textPrimary
                        ),
                        cursorBrush = SolidColor(MechtaTheme.colors.brandBaseBrand),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { onSubscribeClick() }
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = onSubscribeClick,
                    enabled = !isLoading && email.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MechtaTheme.colors.brandBaseBrand,
                        contentColor = MechtaTheme.colors.brandBaseBackground
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MechtaTheme.colors.brandBaseBackground,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("OK")
                    }
                }
            }
        }
    }
}

@Composable
private fun SocialsSection(
    socials: List<SocialModel>,
    onSocialClick: (SocialModel) -> Unit
) {
    val isDark = isSystemInDarkTheme()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        socials.forEach { social ->
            val iconUrl = if (isDark) social.iconDark else social.iconLight
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MechtaTheme.colors.baseGeneric)
                    .clickable { onSocialClick(social) },
                contentAlignment = Alignment.Center
            ) {
                CustomImageView(
                    model = iconUrl.ifEmpty { social.iconLight },
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}
