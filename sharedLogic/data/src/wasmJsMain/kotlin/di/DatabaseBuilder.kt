package com.example.jetcaster.core.data.di

import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.jetcaster.core.data.database.JetcasterDatabase

fun getDatabaseBuilder(): RoomDatabase.Builder<JetcasterDatabase> {
    return Room.inMemoryDatabaseBuilder<JetcasterDatabase>()
        .setDriver(createSQLiteDriver())
}
