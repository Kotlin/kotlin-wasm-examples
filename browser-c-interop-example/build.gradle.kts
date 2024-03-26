import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.gradle.targets.js.ir.DefaultIncrementalSyncTask
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import java.util.*

plugins {
    kotlin("multiplatform") version "1.9.22"
}

repositories {
    mavenCentral()
}

kotlin {
    wasmJs {
        binaries.executable()
        browser()
    }
}

val props = Properties().apply {
    file("local.properties").inputStream().use(::load)
}

val compileNativeSources by tasks.creating(Exec::class) {
    commandLine(props.getProperty("PATH_TO_CLANG") ?: error("Provide PATH_TO_CLANG inside local.properties file"), "--target=wasm32", "-nostdlib", "-Wl,--export-all", "-Wl,--no-entry", "-o", "build/clang/lib.wasm", "src/nativeSources/lib.c")
    outputs.file("build/clang/lib.wasm")
}

val copyNativeBinariesAndGlueCode by tasks.creating(Copy::class) {
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

    into(syncTask.get().destinationDirectory.get())
}

tasks.withType<Kotlin2JsCompile>().configureEach {
    dependsOn(copyNativeBinariesAndGlueCode)
}

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "21.0.0-v8-canary202309143a48826a08"
    nodeDownloadBaseUrl = "https://nodejs.org/download/v8-canary"
}

tasks.withType<KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}
