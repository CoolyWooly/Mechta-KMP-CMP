package kz.mechta.feature_catalog.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kz.mechta.core_ui.components.CustomImageView
import kz.mechta.core_ui.components.ShimmerBox
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.gift_cards
import kz.mechta.core_ui.generated.resources.img_gift_card
import kz.mechta.core_ui.generated.resources.img_promotions_percent
import kz.mechta.core_ui.generated.resources.promotions
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.feature_catalog.domain.model.CatalogModel
import kz.mechta.feature_catalog.presentation.util.GIFT_CARDS_LINK
import kz.mechta.feature_catalog.presentation.util.PROMOTIONS_LINK
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun TabCatalog(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    catalogList: List<CatalogModel>,
    onCategoryClick: (String) -> Unit,
    onFeatureClick: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            CategoryView(
                name = stringResource(Res.string.promotions),
                image = Res.drawable.img_promotions_percent,
                onClick = { onCategoryClick(PROMOTIONS_LINK) }
            )
        }

        item {
            CategoryView(
                name = stringResource(Res.string.gift_cards),
                image = Res.drawable.img_gift_card,
                onClick = { onCategoryClick(GIFT_CARDS_LINK) }
            )
        }

        items(catalogList) { item ->
            CategoryView(
                name = item.name,
                image = item.image,
                onClick = { onCategoryClick(item.url) }
            )
        }
        if (isLoading) {
            items(10) {
                CategoryLoading()
            }
        }
    }
}

@Composable
private fun CategoryView(
    name: String,
    image: Any,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(109.dp)
            .background(
                color = MechtaTheme.colors.brandBaseBackground,
                shape = RoundedCornerShape(MechtaTheme.radius.l)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }

    ) {
        CustomImageView(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .size(60.dp)
                .align(Alignment.BottomEnd),
            model = image,
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier.padding(12.dp),
            text = name,
            style = MechtaTheme.typography.headerSubheader1,
            color = MechtaTheme.colors.textPrimary
        )
    }
}

@Composable
private fun CategoryLoading() {
    ShimmerBox(
        modifier = Modifier
            .fillMaxWidth()
            .height(109.dp)
            .clip(RoundedCornerShape(8.dp)),
    )
}