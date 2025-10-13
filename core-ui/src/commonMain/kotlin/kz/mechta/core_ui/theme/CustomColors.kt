package kz.mechta.core_ui.theme

import androidx.compose.ui.graphics.Color

data class CustomColors(

    // Brand
    val brandBaseBackground: Color,
    val brandBaseBrand: Color,
    val brandBaseBrandHover: Color,
    val brandBaseBrandSecondary: Color,
    val brandBaseBrandSecondaryHover: Color,
    val brandBaseSelection: Color,
    val brandBaseSelectionHover: Color,
    val brandLineBrand: Color,
    val brandTextBrand: Color,
    val brandTextBrandHeavy: Color,
    val brandTextBrandContrast: Color,
    val brandTextLink: Color,
    val brandTextLinkHover: Color,

    // Text
    val textPrimary: Color,
    val textComplementary: Color,
    val textSecondary: Color,
    val textHint: Color,
    val textInfo: Color,
    val textInfoHeavy: Color,
    val textDanger: Color,
    val textDangerHeavy: Color,
    val textWarning: Color,
    val textWarningHeavy: Color,
    val textPositive: Color,
    val textPositiveHeavy: Color,
    val textMisc: Color,
    val textMiscHeavy: Color,
    val textInvertedPrimary: Color,
    val textInvertedComplementary: Color,
    val textInvertedSecondary: Color,
    val textInvertedHint: Color,

    // Base generic/light
    val baseGeneric: Color,
    val baseGenericSolid: Color,
    val baseGenericHover: Color,
    val baseGenericDisabled: Color,
    val baseGenericMedium: Color,
    val baseGenericMediumHover: Color,
    val baseSimpleHover: Color,

    // Base light backgrounds
    val baseLight: Color,
    val baseLightHover: Color,
    val baseLightSimpleHover: Color,
    val baseLightDisabled: Color,

    // Semantic backgrounds
    val baseSemanticInfoLight: Color,
    val baseSemanticInfoLightHover: Color,
    val baseSemanticPositiveLight: Color,
    val baseSemanticPositiveLightHover: Color,
    val baseSemanticWarningLight: Color,
    val baseSemanticWarningLightHover: Color,
    val baseSemanticDangerLight: Color,
    val baseSemanticDangerLightHover: Color,
    val baseSemanticNeutralLight: Color,
    val baseSemanticNeutralLightHover: Color,
    val baseSemanticMiscLight: Color,
    val baseSemanticMiscLightHover: Color,

    // Lines & scroll
    val lineGeneric: Color,
    val lineGenericHover: Color,
    val lineGenericActive: Color,
    val lineGenericSolid: Color,
    val lineInfo: Color,
    val linePositive: Color,
    val lineWarning: Color,
    val lineDanger: Color,
    val lineMisc: Color,
    val miscScrollHandle: Color,
    val miscScrollHandleHover: Color,
    val miscScrollTrack: Color
)


fun getColors(isDark: Boolean): CustomColors {
    return CustomColors(

        // Brand
        brandBaseBackground             = if (isDark) Color(0xFF2D282D) else Color(0xFFFFFFFF),
        brandBaseBrand                  = if (isDark) Color(0xFFB1125A) else Color(0xFFE61771),
        brandBaseBrandHover             = if (isDark) Color(0xFFBE276B) else Color(0xFFC1135F),
        brandBaseBrandSecondary         = if (isDark) Color(0xBFFFFFFF) else Color(0xBF000000),
        brandBaseBrandSecondaryHover    = if (isDark) Color(0xCCFFFFFF) else Color(0xCC000000),
        brandBaseSelection              = if (isDark) Color(0x33B1125A) else Color(0x33E61771),
        brandBaseSelectionHover         = if (isDark) Color(0x4CB1125A) else Color(0x4CE61771),
        brandLineBrand                  = if (isDark) Color(0xFFBE276B) else Color(0xFFC1135F),
        brandTextBrand                  = if (isDark) Color(0xFFB1125A) else Color(0xFFE61771),
        brandTextBrandHeavy             = if (isDark) Color(0xFFBE276B) else Color(0xFFC1135F),
        brandTextBrandContrast          = if (isDark) Color(0xD8FFFFFF) else Color(0xD8FFFFFF),
        brandTextLink                   = if (isDark) Color(0xFFBE276B) else Color(0xFFC1135F),
        brandTextLinkHover              = if (isDark) Color(0xFFCB3C7C) else Color(0xFF9C0F4D),

        // Text
        textPrimary                     = if (isDark) Color(0xD8FFFFFF) else Color(0xD8000000),
        textComplementary               = if (isDark) Color(0xBFFFFFFF) else Color(0xBF000000),
        textSecondary                   = if (isDark) Color(0x7FFFFFFF) else Color(0x7F000000),
        textHint                        = if (isDark) Color(0x4CFFFFFF) else Color(0x4C000000),
        textInfo                        = if (isDark) Color(0xFF3697F1) else Color(0xFF2C82D2),
        textInfoHeavy                   = if (isDark) Color(0xFF2C82D2) else Color(0xFF236DB3),
        textDanger                      = if (isDark) Color(0xFFFF003D) else Color(0xFFFF003D),
        textDangerHeavy                 = if (isDark) Color(0xFFD60033) else Color(0xFFAD0029),
        textWarning                     = if (isDark) Color(0xFFFFBE5C) else Color(0xFFE9A94A),
        textWarningHeavy                = if (isDark) Color(0xFFE9A94A) else Color(0xFFD29437),
        textPositive                    = if (isDark) Color(0xFF32BA76) else Color(0xFF299A62),
        textPositiveHeavy               = if (isDark) Color(0xFF299A62) else Color(0xFF217A4D),
        textMisc                        = if (isDark) Color(0xFF60809C) else Color(0xFF5A6F81),
        textMiscHeavy                   = if (isDark) Color(0xFF506B83) else Color(0xFF495A69),
        textInvertedPrimary             = if (isDark) Color(0xE5000000) else Color(0xFFFFFFFF),
        textInvertedComplementary       = if (isDark) Color(0xBF000000) else Color(0xD8FFFFFF),
        textInvertedSecondary           = if (isDark) Color(0xBF000000) else Color(0xBFFFFFFF),
        textInvertedHint                = if (isDark) Color(0x4C000000) else Color(0x7FFFFFFF),

        // Base generic/light
        baseGeneric                     = if (isDark) Color(0x19FFFFFF) else Color(0x0C000000),
        baseGenericSolid                = if (isDark) Color(0xFF383438) else Color(0xFFF2F2F2),
        baseGenericHover                = if (isDark) Color(0x26FFFFFF) else Color(0x26000000),
        baseGenericDisabled             = if (isDark) Color(0x11FFFFFF) else Color(0x11000000),
        baseGenericMedium               = if (isDark) Color(0x26FFFFFF) else Color(0x26000000),
        baseGenericMediumHover          = if (isDark) Color(0x3FFFFFFF) else Color(0x3F000000),
        baseSimpleHover                 = if (isDark) Color(0x19FFFFFF) else Color(0x0C000000),

        // Base light backgrounds
        baseLight                       = if (isDark) Color(0xD8FFFFFF) else Color(0xFFFFFFFF),
        baseLightHover                  = if (isDark) Color(0xBFFFFFFF) else Color(0xD8FFFFFF),
        baseLightSimpleHover            = if (isDark) Color(0x26FFFFFF) else Color(0x26FFFFFF),
        baseLightDisabled               = if (isDark) Color(0x26FFFFFF) else Color(0x26FFFFFF),

        // Semantic backgrounds
        baseSemanticInfoLight           = if (isDark) Color(0x263697F1) else Color(0x263697F1),
        baseSemanticInfoLightHover      = if (isDark) Color(0x3F3697F1) else Color(0x3F3697F1),
        baseSemanticPositiveLight       = if (isDark) Color(0x2632BA76) else Color(0x2632BA76),
        baseSemanticPositiveLightHover  = if (isDark) Color(0x3F32BA76) else Color(0x3F32BA76),
        baseSemanticWarningLight        = if (isDark) Color(0x26FFBE5C) else Color(0x26FFBE5C),
        baseSemanticWarningLightHover   = if (isDark) Color(0x3FFFBE5C) else Color(0x3FFFBE5C),
        baseSemanticDangerLight         = if (isDark) Color(0x26FF003D) else Color(0x26FF003D),
        baseSemanticDangerLightHover    = if (isDark) Color(0x3FFF003D) else Color(0x3FFF003D),
        baseSemanticNeutralLight        = if (isDark) Color(0x19FFFFFF) else Color(0x0C000000),
        baseSemanticNeutralLightHover   = if (isDark) Color(0x26FFFFFF) else Color(0x19000000),
        baseSemanticMiscLight           = if (isDark) Color(0x2660809C) else Color(0x266B8499),
        baseSemanticMiscLightHover      = if (isDark) Color(0x5960809C) else Color(0x4C6B8499),

        // Lines & scroll
        lineGeneric                     = if (isDark) Color(0x19FFFFFF) else Color(0x19000000),
        lineGenericHover                = if (isDark) Color(0x3FFFFFFF) else Color(0x26000000),
        lineGenericActive               = if (isDark) Color(0x4CFFFFFF) else Color(0x4C000000),
        lineGenericSolid                = if (isDark) Color(0xFF433F43) else Color(0xFFE5E5E5),
        lineInfo                        = if (isDark) Color(0xCC3697F1) else Color(0xCC3697F1),
        linePositive                    = if (isDark) Color(0xCC32BA76) else Color(0xCC32BA76),
        lineWarning                     = if (isDark) Color(0xCCFFBE5C) else Color(0xCCFFBE5C),
        lineDanger                      = if (isDark) Color(0xCCFF003D) else Color(0xCCFF003D),
        lineMisc                        = if (isDark) Color(0x7260809C) else Color(0x726B8499),
        miscScrollHandle                = if (isDark) Color(0x26FFFFFF) else Color(0x19000000),
        miscScrollHandleHover           = if (isDark) Color(0x3FFFFFFF) else Color(0x26000000),
        miscScrollTrack                 = if (isDark) Color(0xFF2D282D) else Color(0xFFFFFFFF)
    )
}