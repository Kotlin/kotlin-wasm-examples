import { instantiate } from './composeApp.uninstantiated.mjs';

await wasmSetup;

instantiate({ skia: Module['asm'] });