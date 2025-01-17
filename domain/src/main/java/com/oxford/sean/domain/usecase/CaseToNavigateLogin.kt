package com.oxford.sean.domain.usecase

import com.oxford.sean.domain.PresGateway
import javax.inject.Inject

class CaseToNavigateLogin @Inject constructor(private val presGateway: PresGateway) {

    suspend fun launch() {
        presGateway.goToLogin()
    }

}