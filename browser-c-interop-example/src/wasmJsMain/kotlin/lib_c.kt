@file:JsModule("./lib.c.mjs")

@JsName("default")
external object LIB_C {
    fun add(a: Int, b: Int): Int
}