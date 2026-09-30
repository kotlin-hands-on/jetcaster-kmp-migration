package com.example.jetcaster.core.data.network

import com.plusmobileapps.konnectivity.Konnectivity

class IOSOnlineChecker() : OnlineChecker {

    val konnectivity: Konnectivity = Konnectivity()

    override fun checkIfOnline(): Boolean {
        return konnectivity.isConnected
    }
}