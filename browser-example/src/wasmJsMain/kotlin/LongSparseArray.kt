internal val EMPTY_LONGS = LongArray(0)
internal val EMPTY_OBJECTS = arrayOfNulls<Any>(0)

public open class LongSparseArray<E> public constructor(initialCapacity: Int) {
    internal  var garbage = false
    internal  var keys: LongArray
    internal  var values: Array<Any?>
    internal  var size = 0

    init {
        if (initialCapacity == 0) {
            keys = EMPTY_LONGS
            values = EMPTY_OBJECTS
        } else {
            val idealCapacity = 10
            keys = LongArray(idealCapacity)
            values = arrayOfNulls(idealCapacity)
        }
    }
    public  open operator fun get(key: Long): E? = commonGet(key)
    @Suppress("KotlinOperator") // Avoid confusion with matrix access syntax.
    public  open fun get(key: Long, defaultValue: E): E = commonGet(key, defaultValue)
    public  open fun put(key: Long, value: E): Unit = commonPut(key, value)
}


private val DELETED = Any()

@Suppress("NOTHING_TO_INLINE")
internal inline fun <E> LongSparseArray<E>.commonGet(key: Long): E? {
    // @Suppress("UNCHECKED_CAST")
    return commonGetInternal(key, null) // as E?
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun <E> LongSparseArray<E>.commonGet(key: Long, defaultValue: E): E {
    // @Suppress("UNCHECKED_CAST")
    return commonGetInternal(key, defaultValue) // as E
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun <T : E?, E> LongSparseArray<E>.commonGetInternal(
    key: Long,
    defaultValue: T
): T {
    val i = key.toInt()
    return if (i < 0 || values[i] === DELETED) { // without this `if`, it doesn't crash
        defaultValue
    } else {
        @Suppress("UNCHECKED_CAST")
        values[key.toInt()] as T
    } // as T //  casting here helps too
}

// This is a workaround we use in Compose sources

//@Suppress("NOTHING_TO_INLINE")
//internal inline fun LongSparseArray<*>.commonGetInternal(
//    key: Long,
//    defaultValue: Any?
//): Any? {
//    val i = key.toInt()
//    return if (i < 0 || values[i] === DELETED) {
//        defaultValue
//    } else {
//        values[i]
//    }
//}

@Suppress("NOTHING_TO_INLINE")
internal inline fun <E> LongSparseArray<E>.commonPut(key: Long, value: E) {
    values[key.toInt()] = value
    return
}