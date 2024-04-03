import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

plugins {
    kotlin("multiplatform") version "1.9.20"
    // kotlin("multiplatform") version "2.0.0-Beta1"
}

repositories {
    mavenCentral()
}

kotlin {
    wasmJs {
        binaries.executable()
        nodejs()
    }
}

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "22.0.0-nightly202404032241e8c5b3"
    nodeDownloadBaseUrl = "https://nodejs.org/download/nightly"
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}
