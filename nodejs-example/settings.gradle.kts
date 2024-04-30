rootProject.name = "kotlin-wasm-nodejs-example"

pluginManagement {
    val kotlin_repo_url: String? by settings
    val kotlin_version: String? by settings

    resolutionStrategy {
        repositories {
            gradlePluginPortal()

            kotlin_repo_url?.also { maven(it) }
        }
    }

    plugins {
        kotlin("multiplatform") version kotlin_version
    }
}
