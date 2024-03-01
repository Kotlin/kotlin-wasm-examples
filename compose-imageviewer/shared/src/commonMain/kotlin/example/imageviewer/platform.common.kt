package example.imageviewer

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import imageviewer.shared.generated.resources.*
import imageviewer.shared.generated.resources.Res.readBytes
import org.jetbrains.compose.resources.ExperimentalResourceApi

expect fun Modifier.notchPadding(): Modifier


private val cache = mutableStateMapOf<String, Painter>()

private sealed class LoadState<T> {
    class Loading<T> : LoadState<T>()
    data class Success<T>(val value: T) : LoadState<T>()
    data class Error<T>(val exception: Exception) : LoadState<T>()
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun rememberImageBitmap(res: String): LoadState<ImageBitmap> {
    val state: MutableState<LoadState<ImageBitmap>> = remember(Res) { mutableStateOf(LoadState.Loading()) }
    LaunchedEffect(Res) {
        state.value = try {
            LoadState.Success(readBytes(res).toImageBitmap())
        } catch (e: Exception) {
            LoadState.Error(e)
        }
    }
    return state.value
}

private fun <T> LoadState<T>.orEmpty(emptyValue: T): T = when (this) {
    is LoadState.Loading -> emptyValue
    is LoadState.Error -> emptyValue
    is LoadState.Success -> this.value
}

private val emptyImageBitmap: ImageBitmap by lazy { ImageBitmap(1, 1) }


@Composable
internal fun painterResourceCached(res: String): Painter {
    return if (cache.containsKey(res)) {
        cache[res]!!
    } else {
        val rib = rememberImageBitmap(res)
        if (rib !is LoadState.Success<ImageBitmap>) {
            BitmapPainter(rib.orEmpty(emptyImageBitmap))
        } else {
            cache[res] = BitmapPainter(rib.orEmpty(emptyImageBitmap))
            cache[res]!!
        }
    }
}
