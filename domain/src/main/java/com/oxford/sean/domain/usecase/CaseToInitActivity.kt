package com.oxford.sean.domain.usecase

import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.PresGateway
import javax.inject.Inject

class CaseToInitActivity @Inject constructor(
    private val dataGateway: DataGateway,
    private val presGateway: PresGateway
) {

    suspend fun launch() {
        if (dataGateway.isUserLoggedIn()) {
            presGateway.goToAppointments(clearStack = true)
        }
    }

}