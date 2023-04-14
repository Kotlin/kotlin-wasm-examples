import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import example.imageviewer.*
import example.imageviewer.model.*
import kotlin.wasm.unsafe.*

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("ImageViewer") {
        ImageViewerWeb()
    }
}
