package com.oxford.sean.domain.usecase

import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.PresGateway
import com.oxford.sean.domain.entity.ToastDurationEntityType
import com.oxford.sean.util.UnauthorizedRequestException
import com.oxford.sean.util.NoInternetException
import javax.inject.Inject

class CaseToLogin @Inject constructor(private val dataGateway: DataGateway, private val presGateway: PresGateway) {

    suspend fun launch(username: String, password: String) {
        try {
            presGateway.showLoadingDialog()
            dataGateway.login(username, password)
            presGateway.hideLoadingDialog()
            presGateway.showToast("Login Successful!", ToastDurationEntityType.SHORT)
            presGateway.goToAppointments(clearStack = true)
        } catch (e: Exception) {
            val toastMessage = when (e) {
                is UnauthorizedRequestException -> "Invalid Login"
                is NoInternetException -> "Unable to connect"
                else -> "Unknown error occurred"
            }
            presGateway.hideLoadingDialog()
            presGateway.showToast(toastMessage, ToastDurationEntityType.SHORT)
        }
    }
}