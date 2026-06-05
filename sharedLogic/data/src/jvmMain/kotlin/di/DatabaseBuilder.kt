package com.example.jetcaster.core.data.di

import java.io.File
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.jetcaster.core.data.database.JetcasterDatabase

fun getDatabaseBuilder(): RoomDatabase.Builder<JetcasterDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<JetcasterDatabase>(
        name = dbFile.absolutePath,
    )
}