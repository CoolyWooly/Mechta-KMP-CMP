package kz.mechta.core_ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mechtakmp.core_ui.generated.resources.Res
import mechtakmp.core_ui.generated.resources.lato_bold
import mechtakmp.core_ui.generated.resources.lato_regular

data class CustomTypography(
    val textCaption1: TextStyle,
    val textCaption2: TextStyle,
    val textBody1Short: TextStyle,
    val textBody1: TextStyle,
    val textBody2: TextStyle,
    val textBody3: TextStyle,
    val headerSubheader1: TextStyle,
    val headerSubheader2: TextStyle,
    val headerSubheader3: TextStyle,
    val headerHeader1: TextStyle,
    val headerHeader2: TextStyle,
    val displayDisplay1: TextStyle,
    val displayDisplay2: TextStyle,
    val displayDisplay3: TextStyle,

    val displayDisplay4: TextStyle,
)

//private fun getFontFamily(): FontFamily {
//    return FontFamily()
//}

private val fontFamily = FontFamily(
//    Font(Res.font.lato_regular),
//    Font(Res.font.lato_bold, FontWeight.Bold),
)

fun getTypography() : CustomTypography {
    return CustomTypography(

        textCaption1 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 9.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.W400
        ),
        textCaption2 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        textBody1Short = TextStyle(
            fontFamily = fontFamily,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        textBody1 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Normal
        ),
        textBody2 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal
        ),
        textBody3 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Normal
        ),
        headerSubheader1 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        headerSubheader2 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        headerSubheader3 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        headerHeader1 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        headerHeader2 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold
        ),
        displayDisplay1 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            fontWeight = FontWeight.Bold
        ),
        displayDisplay2 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold
        ),
        displayDisplay3 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 40.sp,
            lineHeight = 48.sp,
            fontWeight = FontWeight.Bold
        ),
        displayDisplay4 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 48.sp,
            lineHeight = 52.sp,
            fontWeight = FontWeight.Bold
        ),
    )
}
