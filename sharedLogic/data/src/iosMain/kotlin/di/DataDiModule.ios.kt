package com.example.jetcaster.core.data.di

import androidx.room.RoomDatabase
import com.example.jetcaster.core.data.database.JetcasterDatabase
import com.example.jetcaster.core.data.network.IOSOnlineChecker
import com.example.jetcaster.core.data.network.OnlineChecker
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDataModule: Module = module {
    single<RoomDatabase.Builder<JetcasterDatabase>> {
        getDatabaseBuilder()
    }

    single<OnlineChecker> {
        IOSOnlineChecker()
    }
}