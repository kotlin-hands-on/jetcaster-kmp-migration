package com.example.jetcaster.core.data.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.jetcaster.core.data.network.OnlineChecker
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDataModule: Module = module {
    single {
        getDatabaseBuilder(get<Context>())
    }

    single<OnlineChecker> {
        object : OnlineChecker {
            override fun checkIfOnline(): Boolean {
                val context = get<Context>()

                val cm = context.getSystemService(ConnectivityManager::class.java)

                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val capabilities = cm?.getNetworkCapabilities(cm.activeNetwork) ?: return false
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                } else {
                    cm?.activeNetworkInfo?.isConnectedOrConnecting == true
                }
            }
        }

    }
}