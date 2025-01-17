package com.oxford.sean.domain.usecase

import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.PresGateway
import javax.inject.Inject

class CaseToLogout @Inject constructor(private val dataGateway: DataGateway, private val presGateway: PresGateway) {

    suspend fun launch() {
        dataGateway.logout()
        presGateway.goToAppIntro(clearStack = true)
    }

}