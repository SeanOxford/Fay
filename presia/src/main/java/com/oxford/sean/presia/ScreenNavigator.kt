package com.oxford.sean.presia

interface ScreenNavigator {

    suspend fun goToAppIntro(clearStack: Boolean)

    suspend fun goToAppointments(clearStack: Boolean)

    suspend fun goToLogin()

}