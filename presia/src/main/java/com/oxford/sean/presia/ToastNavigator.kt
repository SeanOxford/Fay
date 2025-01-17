package com.oxford.sean.presia

interface ToastNavigator {

    suspend fun showToast(message: String, duration: Int)

}