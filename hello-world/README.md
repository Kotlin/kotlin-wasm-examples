# Kotlin/Wasm Browser Example

A simple app printing "Hello World" in the browser alert box

## Build and run

Check out the repo, navigate to the project folder, and use the following command:
```
./gradlew wasmJsBrowserRun -t
```

## Setup Environment

To run applications built with Kotlin/Wasm in a browser, you need a browser supporting [wasm garbage collection feature](https://github.com/WebAssembly/gc):

- For **Chrome** and **Chromium-based** browsers (Edge, Brave etc.), it **should just work** since version 119.
- For **Firefox** 120 it **should just work**.
- For **Firefox** 119:
    1. Open `about:config` in the browser.
    2. Enable **javascript.options.wasm_gc**.
    3. Refresh the page.

For more information see https://kotl.in/wasm_help/.

## IDE

We recommend using [IntelliJ IDEA 2023.1 or newer](https://www.jetbrains.com/idea/) to work with the project.

# Feedback & Questions

Give it a try, and share your feedback and ask questions in the Kotlin Slack [#webassembly](https://slack-chats.kotlinlang.org/c/webassembly) channel (if you’re not a member, [apply here](https://kotl.in/slack)) or on Twitter to [@bashorov](https://twitter.com/bashorov).

# Learn more

* [Kotlin/Wasm](https://kotl.in/wasm/)
* [Other examples](../../../#examples)