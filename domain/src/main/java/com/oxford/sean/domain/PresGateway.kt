package com.oxford.sean.domain

import com.oxford.sean.domain.entity.ToastDurationEntityType

interface PresGateway {

    suspend fun goToAppIntro(clearStack: Boolean = false)

    suspend fun goToAppointments(clearStack: Boolean = false)

    suspend fun goToLogin()

    suspend fun showLoadingDialog()

    suspend fun hideLoadingDialog()

    suspend fun showToast(message: String, duration: ToastDurationEntityType)

}