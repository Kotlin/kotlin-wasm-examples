# Kotlin/Wasm WASI example

This example shows a simple application using WASI API in Node.js and Deno.

> **Note:**
> This example uses a version of 21.0.0-v8-canary.

In the next sections, you can find information try out this application built with Kotlin/Wasm.

## Set up the environment

Before you start, prepare the IDE you require to run the application.

### IDE

We recommend using [IntelliJ IDEA 2023.1 or later](https://www.jetbrains.com/idea/) to work with the project.
It supports Kotlin/Wasm out of the box.

## Build and run

To build and run the application:

1. In IntelliJ IDEA, open the repository.
2. Navigate to the `wasi-example` project folder.
3. Run the application by using the commands explained in the following sections.

### Run the program via Gradle with NodeJs

To run the program, type the following Gradle command in the terminal:

`./gradlew wasmWasiNodeRun`

### Run tests via Gradle with NodeJs

To run tests, type the following Gradle command in the terminal:

`./gradlew wasmWasiNodeTest`

### Run the program via Gradle with Deno

To run the program with Deno, type the following Gradle command in the terminal:

`./gradlew wasmWasiDenoRun`

### Run tests via Gradle with Deno

To run tests with Deno, type the following Gradle command in the terminal:

`./gradlew wasmWasiDenoTest`

> **Note:**
> For Windows platform, `deno.exe` is expected to be installed. For more information, 
> see [Deno's installation documentation](https://docs.deno.com/runtime/manual/getting_started/installation).

## Feedback and questions

Give it a try and share your feedback or questions in our [#webassembly](https://slack-chats.kotlinlang.org/c/webassembly) 
Slack channel. [Get a Slack invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up).
You can also share your comments with [@bashorov](https://twitter.com/bashorov) on X (Twitter).

## Learn more

* [Kotlin/Wasm](https://kotl.in/wasm/)
* [Other Kotlin/Wasm examples](https://github.com/Kotlin/kotlin-wasm-examples/tree/main)