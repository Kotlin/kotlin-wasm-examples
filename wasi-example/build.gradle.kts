import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

plugins {
    kotlin("multiplatform") version "1.9.20-Beta-224"
}

repositories {
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    mavenCentral()
}

kotlin {
    wasmWasi {
        nodejs()
    }

    sourceSets {
        val wasmWasiTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test")
            }
        }
    }
}

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "20.2.0"
}
