import com.example.jetsnack.ui.components.prepareImagesCache
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.window.CanvasBasedWindow
import com.example.jetsnack.JetSnackAppEntryPoint
import com.example.jetsnack.ui.theme.Karla
import com.example.jetsnack.ui.theme.Montserrat
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("JetSnack", canvasElementId = "jetsnackCanvas") {
        var loading: Boolean by remember { mutableStateOf(true) }

        if (loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            JetSnackAppEntryPoint()
        }

        LaunchedEffect(Unit) {
            loadMontserratFont()
            loadKarlaFont()
            prepareImagesCache()
            loading = false
        }
    }
}


private suspend fun loadMontserratFont() {
    val regular = loadResource("montserrat_regular.ttf")
    val medium = loadResource("montserrat_medium.ttf")
    val light = loadResource("montserrat_light.ttf")
    val semiBold = loadResource("montserrat_semibold.ttf")

    Montserrat = FontFamily(
        Font(identity = "MontserratRegular", data = regular, weight = FontWeight.Normal),
        Font(identity = "MontserratMedium", data = medium, weight = FontWeight.Medium),
        Font(identity = "MontserratLight", data = light, weight = FontWeight.Light),
        Font(identity = "MontserratSemiBold", data = semiBold, weight = FontWeight.SemiBold),
    )
}

private suspend fun loadKarlaFont() {
    val regular = loadResource("karla_regular.ttf")
    val bold = loadResource("karla_bold.ttf")

    Karla = FontFamily(
        Font(identity = "KarlaRegular", data = regular, weight = FontWeight.Normal),
        Font(identity = "KarlaBold", data = bold, weight = FontWeight.Bold),
    )
}


@OptIn(ExperimentalResourceApi::class)
internal suspend fun loadResource(resourcePath: String): ByteArray {
    return resource(resourcePath).readBytes()
}
