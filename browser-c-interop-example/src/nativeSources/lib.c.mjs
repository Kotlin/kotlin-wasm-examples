async function instantiate(imports={}, runInitializer=true) {
    const wasmFilePath = './lib.wasm';
    const wasmInstance = (await WebAssembly.instantiateStreaming(fetch(wasmFilePath), {})).instance;
    const wasmExports = wasmInstance.exports

    return { instance: wasmInstance,  exports: wasmExports };
}

export default (await instantiate()).exports;
