import myModule from "./kotlin-wasm-browser-example-wasm.mjs"

console.log(myModule.add(1, 2));
const { foo } = myModule;
console.log(myModule.add(1, 2));
