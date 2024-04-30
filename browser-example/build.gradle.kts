import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
}

group = "me.user"
version = "1.0-SNAPSHOT"

val kotlin_repo_url: String? = project.properties["kotlin_repo_url"] as String?

repositories {
    mavenCentral()

    kotlin_repo_url?.also { maven(it) }
}

kotlin {
    wasmJs {
        binaries.executable()
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    // Uncomment and configure this if you want to open a browser different from the system default 
                    // open = mapOf(
                    //     "app" to mapOf(
                    //         "name" to "google chrome"
                    //     )
                    // )

                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.rootDir.path)
                    }
                }
            }

            // Uncomment the next line to apply Binaryen and get optimized wasm binaries
            // applyBinaryen()
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val wasmJsMain by getting
        val wasmJsTest by getting
    }
}