pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }

    plugins {
        val kotlinGeneration = extra["kotlin.generation"]
        kotlin("multiplatform").version(extra["kotlin.version.$kotlinGeneration"] as String)
        kotlin("android").version(extra["kotlin.version.$kotlinGeneration"] as String)
        id("com.android.application").version(extra["agp.version"] as String)
        id("com.android.library").version(extra["agp.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.wasm.version.$kotlinGeneration"] as String)
    }
}

rootProject.name = "compose-jetsnack"

include(":android", ":desktop", ":common", ":web")
