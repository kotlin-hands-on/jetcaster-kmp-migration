// Adds Cross-Origin Isolation headers required for OPFS (used by the SQLite web worker).
// Without these, window.crossOriginIsolated is false and OpfsDb throws at startup.
config.devServer = config.devServer || {};
config.devServer.headers = Object.assign(config.devServer.headers || {}, {
    "Cross-Origin-Opener-Policy": "same-origin",
    "Cross-Origin-Embedder-Policy": "require-corp",
});
