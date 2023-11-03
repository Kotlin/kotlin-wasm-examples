# Jetsnack

Web version of [Jetsnack application](https://github.com/android/compose-samples/tree/main/Jetsnack) built with [Compose Multiplatform for Web](#compose-multiplatform-for-web) and [Kotlin/Wasm](#kotlinwasm).

[![Static Badge](https://img.shields.io/badge/online%20demo%20%F0%9F%9A%80-6b57ff?style=for-the-badge)](https://zal.im/wasm/jetsnack).

![](screenshots/jetsnack.png)

> To learn more about the Jetsnack application, visit the [README.md of the original project](https://github.com/android/compose-samples/tree/main/Jetsnack).

## Kotlin/Wasm

> **Note**
> Kotlin/Wasm is Experimental and may be changed at any time. Use it only for evaluation purposes.
> We would appreciate your feedback on it in the public Slack channel [#webassembly](https://slack-chats.kotlinlang.org/c/webassembly).
> If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/issue/KT-56492).

Kotlin/Wasm is a new experimental target that enables developers to compile Kotlin code to WebAssembly (Wasm).

By compiling Kotlin code to WebAssembly, you can run it on any WebAssembly-compatible environment that meets Kotlin's requirements, including web browsers.
This creates numerous opportunities, such as developing high-performance web applications and serverless functions.

## Compose Multiplatform for Web

> **Note**
> Web support is Experimental and may be changed at any time. Use it only for evaluation purposes.
> We would appreciate your feedback on it in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
> If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

Compose for Web is based on [Kotlin/Wasm](https://kotl.in/wasm), the newest target for Kotlin Multiplatform projects.
It allows you to run your code in the browser with all the benefits that WebAssembly has to offer, such as good and predictable performance for your applications.

## Setup environment

### IDE

We recommend using [IntelliJ IDEA 2023.1 or later](https://www.jetbrains.com/idea/) to work with the project.
It has Kotlin/Wasm support out of the box.

### Browser (for Kotlin/Wasm target)

To run applications built with Kotlin/Wasm in a browser, you need a browser supporting [wasm garbage collection feature](https://github.com/WebAssembly/gc):

- For **Chrome** and **Chromium-based** browsers (Edge, Brave etc.), it **should just work** since version 119.
- For **Firefox** 120 it **should just work**.
- For **Firefox** 119:
    1. Open `about:config` in the browser.
    2. Enable **javascript.options.wasm_gc**.
    3. Refresh the page.


For more information see https://kotl.in/wasm_help/.

## Build and run

Check out the repository, navigate to the project folder, and use the following commands:

### Run Web version via Gradle

Run the following Gradle command in the terminal: `./gradlew :web:wasmJsRun`

Once the application starts, open the following URL in your browser: `http://localhost:8080`

### Run Desktop version via Gradle

Run the following Gradle command in the terminal: `./gradlew :desktop:run`

### Install Android application via Gradle

Run the following Gradle command in the terminal: `./gradlew :android:installDebug`

# Feedback & questions

Give it a try, and share your feedback and ask questions in the Kotlin Slack [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web) channel (if you’re not a member, [apply here](https://kotl.in/slack)) or on Twitter to [@bashorov](https://twitter.com/bashorov).

# Learn more

* [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform)
* [Kotlin/Wasm](https://kotl.in/wasm/)
* [Other examples](../../../#examples)
