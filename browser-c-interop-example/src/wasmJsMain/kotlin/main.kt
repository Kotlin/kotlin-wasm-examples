import kotlinx.browser.document
import kotlinx.dom.createElement

fun main() {
    document.body?.apply {
        prepend(document.createElement("h2") {
            textContent = "Calculated result from C for 2 + 2 = ${LIB_C.add(2, 2)}"
        })
    }
}