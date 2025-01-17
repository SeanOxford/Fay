package com.oxford.sean.pres

import android.app.Application
import androidx.navigation.NavController
import com.oxford.sean.presia.ScreenNavigator
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScreenNavigatorImpl @Inject constructor(private val application: Application) : ScreenNavigator {

    private val navController: NavController
        get() = ((application as PresApplication).getActivity() as PresActivity).navController

    override suspend fun goToAppIntro(clearStack: Boolean) {
        withContext(Main) {
            val dest = if (clearStack) R.id.Fragment_app_intro_clear_stack else R.id.Fragment_app_intro
            navController.navigate(dest)
        }
    }

    override suspend fun goToAppointments(clearStack: Boolean) {
        withContext(Main) {
            val dest = if (clearStack) R.id.Fragment_appointments_clear_stack else R.id.Fragment_appointments
            navController.navigate(dest)
        }
    }

    override suspend fun goToLogin() {
        withContext(Main) {
            navController.navigate(R.id.goToLogin)
        }
    }
}