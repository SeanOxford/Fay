package com.oxford.sean.presia

import com.oxford.sean.domain.PresGateway
import com.oxford.sean.domain.entity.ToastDurationEntityType
import com.oxford.sean.presia.transformer.toPres
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PresGatewayImpl @Inject constructor(
    private val screenNavigator: ScreenNavigator,
    private val windowNavigator: WindowNavigator,
    private val toastNavigator: ToastNavigator
) : PresGateway {

    override suspend fun goToAppIntro(clearStack: Boolean) {
        screenNavigator.goToAppIntro(clearStack)
    }

    override suspend fun goToAppointments(clearStack: Boolean) {
        screenNavigator.goToAppointments(clearStack)
    }

    override suspend fun goToLogin() {
        screenNavigator.goToLogin()
    }

    override suspend fun showLoadingDialog() {
        windowNavigator.showLoadingDialog()
    }

    override suspend fun hideLoadingDialog() {
        windowNavigator.hideLoadingDialog()
    }
    override suspend fun showToast(message: String, duration: ToastDurationEntityType) {
        toastNavigator.showToast(message, duration.toPres())
    }
}