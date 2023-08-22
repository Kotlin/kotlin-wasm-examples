import kotlin.test.Test
import kotlin.test.assertTrue

class WasiTest {
    @Test
    fun mainTest() {
        val monotonicTime1 = wasiMonotonicTime()
        val monotinicTime2 = wasiMonotonicTime()
        assertTrue(monotonicTime1 <= monotinicTime2, "Wasi monotonic clock is not monotonic :(")
    }
}