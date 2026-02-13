package kz.mechta.feature_onboarding.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.mechta.core_data.domain.model.CityModel
import kz.mechta.core_ui.generated.resources.Res
import kz.mechta.core_ui.generated.resources.ic_arrow_right
import kz.mechta.core_ui.generated.resources.ic_city_marker
import kz.mechta.core_ui.generated.resources.ic_close_1
import kz.mechta.core_ui.generated.resources.ic_search
import kz.mechta.core_ui.generated.resources.onboarding_text3
import kz.mechta.core_ui.generated.resources.search_city
import kz.mechta.core_ui.generated.resources.select_city
import kz.mechta.core_ui.theme.MechtaTheme
import kz.mechta.core_ui.utils.collectInLaunchedEffect
import kz.mechta.core_ui.utils.use
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingPage(
    navigateToMain: () -> Unit,
) {
    val viewModel = koinViewModel<OnboardingViewModel>()
    val (state, event, effect) = use(viewModel = viewModel)

    Scaffold(
        containerColor = MechtaTheme.colors.brandBaseBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(Res.string.select_city),
                    style = MechtaTheme.typography.displayDisplay1,
                    color = MechtaTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(Res.string.onboarding_text3),
                    style = MechtaTheme.typography.textBody2,
                    color = MechtaTheme.colors.textSecondary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Search field
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = state.searchQuery,
                onValueChange = { event(OnboardingContract.Event.OnSearchQueryChanged(it)) },
                placeholder = stringResource(Res.string.search_city)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when {
                    state.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = MechtaTheme.colors.brandBaseBrand
                        )
                    }
                    state.filteredCities.isEmpty() && state.searchQuery.isNotEmpty() -> {
                        Text(
                            text = "Город не найден",
                            style = MechtaTheme.typography.textBody2,
                            color = MechtaTheme.colors.textSecondary,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(0.dp)
                        ) {
                            items(
                                items = state.filteredCities,
                                key = { it.code }
                            ) { city ->
                                CityItem(
                                    city = city,
                                    onClick = { event(OnboardingContract.Event.OnCityClick(city)) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    effect.collectInLaunchedEffect {
        when (it) {
            is OnboardingContract.Effect.NavigateToMain -> navigateToMain()
        }
    }
}

@Composable
private fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MechtaTheme.colors.baseGeneric)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_search),
            contentDescription = null,
            tint = MechtaTheme.colors.textHint,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Box(modifier = Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = MechtaTheme.typography.textBody2,
                    color = MechtaTheme.colors.textHint
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = MechtaTheme.typography.textBody2.copy(
                    color = MechtaTheme.colors.textPrimary
                ),
                cursorBrush = SolidColor(MechtaTheme.colors.brandBaseBrand),
                modifier = Modifier.fillMaxWidth()
            )
        }
        AnimatedVisibility(
            visible = value.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_close_1),
                contentDescription = null,
                tint = MechtaTheme.colors.textSecondary,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onValueChange("") }
            )
        }
    }
}

@Composable
private fun CityItem(
    city: CityModel,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = onClick)
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MechtaTheme.colors.brandBaseSelection),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_city_marker),
                    contentDescription = null,
                    tint = MechtaTheme.colors.brandBaseBrand,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = city.name,
                style = MechtaTheme.typography.headerSubheader2,
                color = MechtaTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_right),
                contentDescription = null,
                tint = MechtaTheme.colors.textSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
        HorizontalDivider(
            color = MechtaTheme.colors.lineGeneric,
            thickness = 1.dp
        )
    }
}