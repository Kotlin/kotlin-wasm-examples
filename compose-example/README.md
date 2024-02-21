# Compose for web example

This example showcases a web application built with [Compose Multiplatform for web](#compose-multiplatform-for-web) and [Kotlin/Wasm](https://kotl.in/wasm).

![](screenshots/compose-example.png)

## Compose Multiplatform for web

> **Note:**
> Web support is an [Alpha](https://kotlinlang.org/docs/components-stability.html) feature. It may be changed at any time.
> You can use it in scenarios before production. We would appreciate your feedback on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).
>
> [Join the compose-web community](https://slack-chats.kotlinlang.org/c/compose-web).

Compose Multiplatform for web enables sharing your mobile or desktop UIs on the web.

Compose Multiplatform for web is based on [Kotlin/Wasm](https://kotl.in/wasm), the newest target for Kotlin Multiplatform projects.
This enables running your code in the browser, leveraging WebAssembly's advantages like high and consistent application performance.

Follow the instructions in the sections below to try out this application built with Compose Multiplatform for web and Kotlin/Wasm.

## Set up the environment

Before starting, ensure you have the necessary IDE and browser setup to run the application.

### IDE

We recommend using [IntelliJ IDEA 2023.1 or later](https://www.jetbrains.com/idea/) to work with the project.
It supports Kotlin/Wasm out of the box.

### Browser (for Kotlin/Wasm target)

To run applications built with Kotlin/Wasm in a browser, you need a browser supporting the [Wasm Garbage Collection (GC) feature](https://github.com/WebAssembly/gc):

**Chrome and Chromium-based**

* **For version 119 or later:**

  Works by default.

**Firefox**

* **For version 120 or later:**

  Works by default.

**Safari/WebKit**

Wasm GC support is currently under
[active development](https://bugs.webkit.org/show_bug.cgi?id=247394).

> **Note:**
> For more information about the browser versions, see the [Troubleshooting documentation](https://kotl.in/wasm_help/).

## Build and run

To build and run the application:

1. In IntelliJ IDEA, open the repository.
2. Navigate to the `compose-example` project folder.
3. Run the application by typing one of the following Gradle commands in the terminal:

* **Web version:**

  `./gradlew :composeApp:wasmJsRun`
  <br>&nbsp;<br>

  Once the application starts, open the following URL in your browser:

  `http://localhost:8080`

  > **Note:**
  > The port number can vary. If the port 8080 is unavailable, you can find the corresponding port number printed in the console
  > after building the application.
<br>&nbsp;<br>

* **Desktop version:**

  `./gradlew :composeApp:run`

## Feedback and questions

Give it a try and share your feedback or questions in our [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web) Slack channel.
[Get a Slack invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up).
You can also share your comments with [@bashorov](https://twitter.com/bashorov) on X (Twitter).

## Learn more

* [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform)
* [Kotlin/Wasm](https://kotl.in/wasm/)
* [Other Kotlin/Wasm examples](https://github.com/Kotlin/kotlin-wasm-examples/tree/main)
