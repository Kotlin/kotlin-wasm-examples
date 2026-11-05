import java.util.*

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

repositories {
    mavenCentral()
}

kotlin {
    wasmJs {
        binaries.executable()
        browser()
    }

    sourceSets {
        wasmJsMain {
            dependencies {
                implementation(libs.kotlinx.browser)
            }
        }
    }
}

val props = Properties().apply {
    file("local.properties").inputStream().use(::load)
}

val compileNativeSources = tasks.register<Exec>("compileNativeSources") {
    commandLine(
        props.getProperty("PATH_TO_EMCC") ?: error("Provide PATH_TO_EMCC inside local.properties file"),
        "--target=wasm32",
        "-sEXPORT_ALL=1",
        "-Wl,--no-entry",
        "-o",
        "build/clang/lib.wasm",
        "src/nativeSources/lib.c"
    )
    outputs.file("build/clang/lib.wasm")
}

tasks.named<ProcessResources>("wasmJsProcessResources") {
    from(compileNativeSources)
    from("./src/nativeSources/lib.c.mjs")
}