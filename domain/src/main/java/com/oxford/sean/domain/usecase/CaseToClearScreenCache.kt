package com.oxford.sean.domain.usecase

import com.oxford.sean.domain.DataGateway
import javax.inject.Inject

class CaseToClearScreenCache @Inject constructor(private val dataGateway: DataGateway) {

    fun launch(processId: String) {
        dataGateway.invalidateProcesses(processId)
    }

}