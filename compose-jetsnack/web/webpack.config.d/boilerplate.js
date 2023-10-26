config.entry = {
    main: [require('path').resolve(__dirname, "kotlin/load.mjs")]
};

config.resolve = {
    alias: {
        skia: false,
        GL: false,
        SkikoCallbacks: false
    },
};
