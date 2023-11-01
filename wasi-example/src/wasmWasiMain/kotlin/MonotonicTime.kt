import kotlin.wasm.WasmImport
import kotlin.wasm.unsafe.Pointer
import kotlin.wasm.unsafe.UnsafeWasmMemoryApi
import kotlin.wasm.unsafe.withScopedMemoryAllocator

fun main() {
    println("Hello from Kotlin via WASI")
    println("Current timestamp is: ${wasiMonotonicTime()}")
}

@WasmImport("wasi_snapshot_preview1", "clock_time_get")
private external fun wasiRawClockTimeGet(clockId: Int, precision: Long, resultPtr: Int): Int

private const val MONOTONIC = 1

@OptIn(UnsafeWasmMemoryApi::class)
fun wasiMonotonicTime(): Long = withScopedMemoryAllocator { allocator ->
    val rp0 = allocator.allocate(8)
    val ret = wasiRawClockTimeGet(
        clockId = MONOTONIC,
        precision = 1,
        resultPtr = rp0.address.toInt()
    )
    check(ret == 0) {
        "Invalid WASI return code $ret"
    }
    (Pointer(rp0.address.toInt().toUInt())).loadLong()
}
