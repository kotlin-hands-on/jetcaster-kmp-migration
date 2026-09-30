package com.example.jetcaster.core.data.di

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

actual fun createSQLiteDriver(): SQLiteDriver = BundledSQLiteDriver()
