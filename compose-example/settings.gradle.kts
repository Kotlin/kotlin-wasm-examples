rootProject.name = "compose-example"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    val kotlin_repo_url: String? by settings
    val kotlin_version: String? by settings

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()

        kotlin_repo_url?.also { maven(it) }
    }

    plugins {
        kotlin("multiplatform") version kotlin_version
        kotlin("plugin.compose") version kotlin_version
    }
}

dependencyResolutionManagement {
    val kotlin_repo_url: String? by settings

    repositories {
        google()
        mavenCentral()

        kotlin_repo_url?.also { maven(it) }
    }
}

include(":composeApp")