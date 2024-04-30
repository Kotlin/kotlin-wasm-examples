import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsExec
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import java.io.OutputStream

plugins {
    kotlin("multiplatform")
}

val kotlin_repo_url: String? = project.properties["kotlin_repo_url"] as String?

repositories {
    mavenCentral()
    kotlin_repo_url?.also { maven(it) }
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

tasks.withType<NodeJsExec>().all {

    val result = StringBuilder()

    standardOutput = object : OutputStream() {
        override fun write(b: Int) {
            result.append(b.toChar())
        }

    }

    doLast {
        println(result.toString())

        val expectedString1 = "Hello World!"

        val lines = result.lines().filter { it.isNotEmpty() }
        check(lines.size == 1) {
            "Expected 1 lines, actual: ${lines.size}"
        }

        check(lines[0] == expectedString1) {
            "Expected '$expectedString1', actual: '${lines[0]}'"
        }
    }
}