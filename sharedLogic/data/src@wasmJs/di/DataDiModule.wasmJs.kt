package com.example.jetcaster.core.data.di

import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.jetcaster.core.data.database.JetcasterDatabase
import com.example.jetcaster.core.data.network.OnlineChecker
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDataModule: Module = module {
    single<RoomDatabase.Builder<JetcasterDatabase>> {
        Room.inMemoryDatabaseBuilder<JetcasterDatabase>()
    }

    single<OnlineChecker> {
        object : OnlineChecker {
            override fun checkIfOnline(): Boolean {
                // TODO: use navigator.onLine for a real connectivity check
                return true
            }
        }
    }
}
