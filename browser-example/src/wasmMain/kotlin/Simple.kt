import kotlinx.browser.document
import kotlinx.dom.appendText
import org.w3c.dom.HTMLDivElement

fun main() {
    (document.getElementById("warning") as HTMLDivElement).style.display = "none"
    document.body?.appendText("Hello, ${greet()}!")
}

fun greet() = "world"