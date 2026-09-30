package com.example.jetcaster.core.data.network

// TODO would be better to use Flows for this, but  to prevent reinventing the logic and focusing just on migration, we keep it in the simple form
interface OnlineChecker {
    fun checkIfOnline(): Boolean
}