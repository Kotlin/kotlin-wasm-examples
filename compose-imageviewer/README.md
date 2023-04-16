# Compose Multiplatform for Web

> **Note**
> Web support is Experimental and may be changed at any time. Use it only for evaluation purposes.
> We would appreciate your feedback on it in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
> If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can experiment with sharing your mobile or desktop UIs with the web. Compose for Web is based on [Kotlin/Wasm](https://kotl.in/wasm),
the newest target for Kotlin Multiplatform projects. It allows Kotlin developers to run their code in the browser with
all the benefits that WebAssembly has to offer, such as good and predictable performance for your applications.

# Image Viewer

Web version of an image gallery for remote server image viewing built with Compose Multiplatform.

![](screenshots/imageviewer.png)

## Build and run

Check out the repo, navigate to the project folder, and use the following commands:

### Run Web version via Gradle

`./gradlew :webApp:wasmRun`

### Run Desktop version via Gradle

`./gradlew :desktopApp:run`

### Install Android application via Gradle

`./gradlew :androidApp:installDebug`

## Setup Environment

>**Note**
> Using experimental Kotlin/Wasm may require enabling experimental features in the target environment.

- **Chrome** 110 or newer: enable **WebAssembly Garbage Collection** at [chrome://flags/#enable-webassembly-garbage-collection](chrome://flags/#enable-webassembly-garbage-collection) or with Chrome 109 or newer, run the program with the `--js-flags=--experimental-wasm-gc` command line argument.
- **Firefox Nightly** 112 or newer: enable **javascript.options.wasm_function_references** and **javascript.options.wasm_gc** at [about:config](about:config).
- **Edge** 109 or newer: run the program with the `--js-flags=--experimental-wasm-gc` command line argument.

For more information see https://kotl.in/wasm_help/.

## IDE

We recommend using [IntelliJ IDEA 2023.1 or newer](https://www.jetbrains.com/idea/) to work with the project.

# Feedback & Questions

Give it a try, and share your feedback and ask questions in the Kotlin Slack [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web) channel (if you’re not a member, [apply here](https://kotl.in/slack)) or on Twitter to [@bashorov](https://twitter.com/bashorov).

# Learn more

* [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform)
* [Kotlin/Wasm](https://kotl.in/wasm/)
* [Other examples](../../../#examples)
