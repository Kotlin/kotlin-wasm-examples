import kotlin.js.*
import org.w3c.fetch.*

external fun fetch(input: String, init: JsAny = definedExternally): Promise<Response>

fun main() {
    println("Hello World!")

    fetch("https://httpbin.org/get")
        .then {
            if (it.ok) {
                it.text().then {
                    println()
                    println("HTTP response:")
                    println(it)
                    null
                }
            } else {
                println("HTTP error: " + it.status)
            }
            null
        }
        .catch {
            println("Error making HTTP request: " + it)
            null
        }
}
