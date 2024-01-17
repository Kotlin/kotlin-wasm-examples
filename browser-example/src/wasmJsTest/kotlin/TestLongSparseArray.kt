import kotlin.test.Test
import kotlin.test.assertEquals

class TestLongSparseArray {

    @Test
    fun itCrashes() {
        val lsa = LongSparseArray<String>(10)
        lsa.put(1, "a")
        lsa.put(2, "b")
        lsa.put(3, "c")
        assertEquals("a", lsa.get(1))
    }
}