package com.example.jetcaster.core.data.di

import androidx.room.RoomDatabase
import com.example.jetcaster.core.data.database.JetcasterDatabase
import com.example.jetcaster.core.data.network.OnlineChecker
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDataModule: Module = module {
    single<RoomDatabase.Builder<JetcasterDatabase>> { getDatabaseBuilder() }

    single<OnlineChecker> {
        object : OnlineChecker {
            override fun checkIfOnline(): Boolean {
                // TODO figure out how to check if it's online
                return true
            }
        }
    }
}