package kz.mechta.core_ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent

@Composable
fun CustomImageView(
    modifier: Modifier = Modifier,
    model: Any?,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = model,
        alignment = Alignment.Center,
        contentScale = contentScale,
        contentDescription = null,
        alpha = alpha
    ) {
        val state = painter.state.collectAsState().value
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            ShimmerBox()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}

//@Composable
//fun MechtaImageView(
//    modifier: Modifier = Modifier,
//    model: ThemedImageModel,
//    contentScale: ContentScale = ContentScale.Fit,
//    alpha: Float = DefaultAlpha,
//) {
//
//    val imageUrl = if (isSystemInDarkTheme()) model.dark else model.light
//
//    MechtaImageView(
//        modifier = modifier,
//        model = imageUrl,
//        contentScale = contentScale,
//        alpha = alpha
//    )
//}