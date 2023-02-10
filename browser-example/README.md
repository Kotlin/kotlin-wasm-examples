# Kotlin/Wasm Browser Example

## Setup

### IDE

We recommend using [IntelliJ IDEA 2023.1 (EAP) or newer](https://www.jetbrains.com/idea/nextversion/).

### Environment

⚠️ Note: Using experimental Kotlin/Wasm may require enabling experimental features in the target environment.

- **Chrome** 110 or newer: enable **WebAssembly Garbage Collection** at [chrome://flags/#enable-webassembly-garbage-collection](chrome://flags/#enable-webassembly-garbage-collection) or with Chrome 109 or newer, run the program with the `--js-flags=--experimental-wasm-gc` command line argument.
- **Firefox** 111 or newer: enable **javascript.options.wasm_function_references** and **javascript.options.wasm_gc** at [about:config](about:config).
- **Edge** 109 or newer: run the program with the `--js-flags=--experimental-wasm-gc` command line argument.

For more information see https://kotl.in/wasm_help/.

## Build and run

To build and run the example execute follow command in a terminal:
```shell
./gradlew wasmBrowserRun -t
```


