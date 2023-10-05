import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

kotlin {
    @OptIn(org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "jetsnackwasmapp"
        browser {
            commonWebpackConfig {
                outputFileName = "jetsnackwasmapp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).copy(
//                    open = mapOf(
//                        "app" to mapOf(
//                            "name" to "google chrome canary"
//                        )
//                    ),
                    static = (devServer?.static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.rootDir.path)
                        add(project.rootDir.path + "/common/")
                        add(project.rootDir.path + "/nonAndroidMain/")
                        add(project.rootDir.path + "/web/")
                    },
                    )
            }
        }
        binaries.executable()
//        applyBinaryen()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)
                implementation(project(":common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}

compose {
    val composeVersion = project.property("compose.compiler.version") as String
    kotlinCompilerPlugin.set(composeVersion)
    val kotlinVersion = project.property("kotlin.version") as String
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
}


// Reason: https://jetbrains.slack.com/archives/C047QCXNLTX/p1694093406932359
project.tasks.getByName("wasmJsDevelopmentExecutableCompileSync").doLast {
    val f = project.buildDir.resolve("../../build/js/packages/jetsnackwasmapp/kotlin/jetsnackwasmapp.uninstantiated.mjs")
        .normalize()
    val t = f.readText().replace("'skia': imports['skia'] ?? await import('skia'),", "'skia': imports['skia'],")
    f.writeText(t)
}