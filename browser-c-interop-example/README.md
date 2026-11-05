[![Official project](https://jb.gg/badges/official-plastic.svg)](https://github.com/JetBrains#jetbrains-on-github)

# Kotlin/Wasm Browser Example with C interop

A simple app calling C function from Kotlin/Wasm browser application.

> **Note:**
> You need [Emscripten](https://emscripten.org/docs/getting_started/downloads.html) to be installed on your machine to compile the example.
> Specify the path to the `emcc` executable in the `PATH_TO_EMCC` property in the `local.properties` file.

## Build and run
To build and run the application:

1. In IntelliJ IDEA, open the repository.
2. Run the application by typing the following Gradle command in the terminal:

   `./gradlew wasmJsBrowserDevelopmentRun`

## Feedback & Questions

Give it a try, and share your feedback and ask questions in the Kotlin Slack [#webassembly](https://slack-chats.kotlinlang.org/c/webassembly) channel (if you’re not a member, [apply here](https://kotl.in/slack)) or on Twitter to [@bashorov](https://twitter.com/bashorov).

## Learn more

* [Kotlin/Wasm](https://kotl.in/wasm/)
* [Other examples](../../../#examples)
