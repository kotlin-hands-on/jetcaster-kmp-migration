package com.example.jetcaster.core.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetcaster.core.data.database.JetcasterDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<JetcasterDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<JetcasterDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}