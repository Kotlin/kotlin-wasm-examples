rootProject.name = "kotlin-wasm-browser-example"

pluginManagement {
    val kotlin_repo_url: String? by settings
    val kotlin_version: String? by settings

    repositories {
        gradlePluginPortal()

        kotlin_repo_url?.also { maven(it) }
    }

    plugins {
        kotlin("multiplatform") version kotlin_version
    }
}
