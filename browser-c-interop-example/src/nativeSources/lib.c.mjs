const { instance } = await WebAssembly.instantiateStreaming(fetch(new URL('./lib.wasm', import.meta.url)));

export default instance.exports;
