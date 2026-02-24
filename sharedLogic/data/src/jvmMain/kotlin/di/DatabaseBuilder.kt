package com.example.jetcaster.core.data.di

import java.io.File
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetcaster.core.data.database.JetcasterDatabase

fun getDatabaseBuilder(): RoomDatabase.Builder<JetcasterDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<JetcasterDatabase>(
        name = dbFile.absolutePath,
    )
}