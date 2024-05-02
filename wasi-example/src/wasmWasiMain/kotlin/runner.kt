import test.exceptionInFinally_box
import kotlin.wasm.WasmExport

@WasmExport
fun runTests() {
    println("Start...\n")

    println("# exceptionInFinally")
    println(exceptionInFinally_box())

    println()

    println("# tryCatchWithHandleResult")
    println(try { tryCatchWithHandleResult_box() } catch (t: Throwable) { "ERROR: " + t.toString() })

    println("\nDone")
}

// Uncomment to run in Node.js 
// fun main() {
//     runTests()
// }