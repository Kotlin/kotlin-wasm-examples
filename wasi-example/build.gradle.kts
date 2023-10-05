import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.Download
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest
import org.jetbrains.kotlin.gradle.testing.internal.KotlinTestReport

plugins {
    kotlin("multiplatform") version "1.9.20-Beta2"
}

repositories {
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    mavenCentral()
}

// Deno tasks
enum class OsName { WINDOWS, MAC, LINUX, UNKNOWN }
enum class OsArch { X86_32, X86_64, ARM64, UNKNOWN }
data class OsType(val name: OsName, val arch: OsArch)

val currentOsType = run {
    val gradleOs = OperatingSystem.current()
    val osName = when {
        gradleOs.isMacOsX -> OsName.MAC
        gradleOs.isWindows -> OsName.WINDOWS
        gradleOs.isLinux -> OsName.LINUX
        else -> OsName.UNKNOWN
    }

    val osArch = when (providers.systemProperty("sun.arch.data.model").forUseAtConfigurationTime().get()) {
        "32" -> OsArch.X86_32
        "64" -> when (providers.systemProperty("os.arch").forUseAtConfigurationTime().get().toLowerCase()) {
            "aarch64" -> OsArch.ARM64
            else -> OsArch.X86_64
        }
        else -> OsArch.UNKNOWN
    }

    OsType(osName, osArch)
}

val unzipDeno = run {
    val denoDirectory = "https://github.com/denoland/deno/releases/latest/download"
    val denoSuffix = when (currentOsType) {
        OsType(OsName.LINUX, OsArch.X86_64) -> "x86_64-unknown-linux-gnu"
        OsType(OsName.MAC, OsArch.X86_64) -> "x86_64-apple-darwin"
        OsType(OsName.MAC, OsArch.ARM64) -> "aarch64-apple-darwin"
        else -> return@run null
    }
    val denoLocation = "$denoDirectory/deno-$denoSuffix.zip"

    val downloadedTools = File(buildDir, "tools")

    val downloadDeno = tasks.register("denoDownload", Download::class) {
        src(denoLocation)
        dest(File(downloadedTools, "deno-$denoSuffix.zip"))
        overwrite(false)
    }

    tasks.register("denoUnzip", Copy::class) {
        dependsOn(downloadDeno)
        from(zipTree(downloadDeno.get().dest))
        val unpackedDir = File(downloadedTools, "deno-$denoSuffix")
        into(unpackedDir)
    }
}

fun getDenoExecutableText(wasmFileName: String): String = """
import Context from "https://deno.land/std@0.201.0/wasi/snapshot_preview1.ts";

const context = new Context({
  args: Deno.args,
  env: Deno.env.toObject(),
});

const binary = await Deno.readFile("./$wasmFileName");
const module = await WebAssembly.compile(binary);
const wasmInstance = await WebAssembly.instantiate(module, {
  "wasi_snapshot_preview1": context.exports,
});

context.initialize(wasmInstance);
wasmInstance.exports.startUnitTests?.();
"""

fun Project.createDenoExecutableFile(
    taskName: String,
    wasmFileName: Provider<String>,
    outputDirectory: Provider<File>,
    resultFileName: String,
): TaskProvider<Task> = tasks.register(taskName, Task::class) {
    doFirst {
        val denoMjs = File(outputDirectory.get(), resultFileName)
        denoMjs.writeText(getDenoExecutableText(wasmFileName.get()))
    }
}

fun Project.createDenoExec(
    nodeMjsFile: RegularFileProperty,
    taskName: String,
    taskGroup: String?
): TaskProvider<Exec> {
    val denoFileName = "runUnitTestsDeno.mjs"

    val outputDirectory = nodeMjsFile.map { it.asFile.parentFile }
    val wasmFileName = nodeMjsFile.map { "${it.asFile.nameWithoutExtension}.wasm" }

    val denoFileTask = createDenoExecutableFile(
        taskName = "${taskName}CreateDinoFile",
        wasmFileName = wasmFileName,
        outputDirectory = outputDirectory,
        resultFileName = denoFileName
    )

    return tasks.register(taskName, Exec::class) {
        if (unzipDeno != null) {
            dependsOn(unzipDeno)
        }
        dependsOn(denoFileTask)

        group = taskGroup

        description = "Executes tests with Deno"

        val newArgs = mutableListOf<String>()

        executable = when (currentOsType.name) {
            OsName.WINDOWS -> "deno.exe"
            else -> unzipDeno?.let { File(unzipDeno.get().destinationDir, "deno").absolutePath } ?: "deno"
        }

        newArgs.add("run")
        newArgs.add("--allow-read")
        newArgs.add("--allow-env")
        newArgs.add("--v8-flags=--experimental-wasm-gc")

        newArgs.add(denoFileName)

        args = newArgs

        workingDir = outputDirectory.get()
    }
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
    nodeVersion = "21.0.0-v8-canary202309143a48826a08"
    nodeDownloadBaseUrl = "https://nodejs.org/download/v8-canary"
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}

tasks.withType<KotlinJsTest>().all {
    val denoExecTask = createDenoExec(
        inputFileProperty,
        name.replace("Node", "Deno"),
        group
    )

    denoExecTask.configure {
        dependsOn (
            project.provider { this@all.taskDependencies }
        )
    }

    tasks.withType<KotlinTestReport> {
        dependsOn(denoExecTask)
    }
}
