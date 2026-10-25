@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.ir.DefaultIncrementalSyncTask
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
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

val compileNativeSources by tasks.registering(Exec::class) {
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

val copyNativeBinariesAndGlueCode by tasks.registering(Copy::class) {
    dependsOn(compileNativeSources)

    from("./build/clang/lib.wasm")
    from("./src/nativeSources/lib.c.mjs")

    val taskName = if (project.hasProperty("isProduction")
        || project.gradle.startParameter.taskNames.contains("installDist")
    ) {
        "wasmJsProductionExecutableCompileSync"
    } else {
        "wasmJsDevelopmentExecutableCompileSync"
    }
    val syncTask = tasks.named<DefaultIncrementalSyncTask>(taskName)

    into(syncTask.flatMap { it.destinationDirectory })
}

tasks.withType<Kotlin2JsCompile>().configureEach {
    dependsOn(copyNativeBinariesAndGlueCode)
}
