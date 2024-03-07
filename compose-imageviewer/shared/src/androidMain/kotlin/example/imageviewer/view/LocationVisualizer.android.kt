package example.imageviewer.view

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import imageviewer.shared.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
internal actual fun LocationVisualizer(modifier: Modifier) {
    Image(
        painter = painterResource(Res.drawable.dummy_map),
        contentDescription = "Map",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
