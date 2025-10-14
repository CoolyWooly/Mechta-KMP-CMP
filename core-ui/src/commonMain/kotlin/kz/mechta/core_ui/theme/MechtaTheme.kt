package kz.mechta.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCustomColors = staticCompositionLocalOf { getColors(false) }
val LocalCustomTypography = staticCompositionLocalOf { getTypography() }
val LocalCustomRadius = staticCompositionLocalOf { getRadius() }

@Composable
fun MechtaTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomColors provides getColors(isDark),
//        LocalCustomTypography provides getTypography(),
        LocalCustomRadius provides getRadius(),
    ) {
        MaterialTheme(
            content = content,
            colorScheme = if (isDark)
                lightColorScheme(primary = MechtaTheme.colors.brandTextBrand)
            else
                lightColorScheme(primary = MechtaTheme.colors.brandTextBrand),
        )
    }
}

object MechtaTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current
    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current
    val radius: CustomRadius
        @Composable
        get() = LocalCustomRadius.current
}