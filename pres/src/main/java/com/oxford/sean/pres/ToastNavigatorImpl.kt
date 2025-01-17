package com.oxford.sean.pres

import android.app.Application
import android.widget.Toast
import com.oxford.sean.presia.ToastNavigator
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToastNavigatorImpl @Inject constructor(private val application: Application) : ToastNavigator {

    override suspend fun showToast(message: String, duration: Int) {
        withContext(Main) { Toast.makeText(application, message, duration).show() }
    }

}