# Kotlin/Wasm Browser Example

A simple app showing "Hello World" in the browser using DOM API.

## Build and run

Check out the repo, navigate to the project folder, and use the following command:
```
./gradlew wasmJsBrowserRun -t
```

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

Give it a try, and share your feedback and ask questions in the Kotlin Slack [#webassembly](https://slack-chats.kotlinlang.org/c/webassembly) channel (if you’re not a member, [apply here](https://kotl.in/slack)) or on Twitter to [@bashorov](https://twitter.com/bashorov).

# Learn more

* [Kotlin/Wasm](https://kotl.in/wasm/)
* [Other examples](../../../#examples)
