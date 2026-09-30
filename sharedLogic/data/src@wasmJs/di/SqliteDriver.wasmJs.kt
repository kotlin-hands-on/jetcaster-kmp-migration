package com.example.jetcaster.core.data.di

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import org.w3c.dom.Worker

@OptIn(ExperimentalWasmJsInterop::class)
fun getWorker(): Worker = js("""new Worker(new URL("sqlite-wasm-worker/worker.js", import.meta.url))""")

actual fun createSQLiteDriver(): SQLiteDriver = WebWorkerSQLiteDriver(
    getWorker()
)
