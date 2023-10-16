import org.jetbrains.compose.ComposeExtension

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }

    configurations.all {
        val conf = this
        // Currently it's necessary to make the android build work properly
        conf.resolutionStrategy.eachDependency {
            if (requested.module.name.startsWith("kotlin-stdlib")) {
                val kotlinVersion = project.property("kotlin.version") as String
                useVersion(kotlinVersion)
            }
            if (requested.module.name.contains("kotlinx-serialization") &&
                // kotlinx-datetime-wasm-js:0.4.1-wasm0 depends on outdated kotlinx-serialization-core:1.5.2-wasm0
                requested.version == "1.5.2-wasm0"
            ) {
                useVersion("1.6.1-wasm0")
            }
        }
    }

    afterEvaluate {
        extensions.findByType(ComposeExtension::class.java)?.apply {
            val composeCompilerVersion = project.property("compose.compiler.version") as String
            kotlinCompilerPlugin.set(composeCompilerVersion)
            val kotlinVersion = project.property("kotlin.version") as String
            kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
        }
    }
}
