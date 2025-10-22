package kz.mechta.feature_catalog.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kz.mechta.core_ui.components.CustomImageView
import kz.mechta.core_ui.components.ShimmerBox
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.feature_catalog.domain.model.BrandModel

@Composable
internal fun TabBrands(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    brandsList: List<BrandModel>,
    onBrandClick: (Int, String, String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(count = 3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (isLoading) {
            items(10) {
                BrandLoading()
            }
        } else {
            itemsIndexed(brandsList) { index, it ->
                BrandItem(
                    image = it.image,
                    onClick = { onBrandClick(index, it.name, it.slug) }
                )
            }
        }
    }
}

@Composable
private fun BrandItem(
    image: BrandModel.BrandImage,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onClick: () -> Unit
) {
    val _image = if (isDarkTheme) image.dark else image.light
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = MechtaTheme.colors.brandBaseBackground,
                shape = RoundedCornerShape(MechtaTheme.radius.s)
            )
            .clip(RoundedCornerShape(MechtaTheme.radius.s))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        CustomImageView(
            modifier = Modifier.fillMaxSize(),
            model = _image,
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun BrandLoading() {
    ShimmerBox(
        modifier = Modifier
            .fillMaxWidth()
            .height(109.dp)
            .clip(RoundedCornerShape(8.dp)),
    )
}