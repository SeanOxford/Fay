package com.oxford.sean.presia.controller

import com.oxford.sean.domain.usecase.CaseToClearScreenCache
import com.oxford.sean.domain.usecase.CaseToLogout
import com.oxford.sean.domain.usecase.CaseToViewAppointments
import com.oxford.sean.presia.model.PresAppointmentScreenViewState
import com.oxford.sean.presia.transformer.toPres
import javax.inject.Inject


class AppointmentsScreenController @Inject constructor(
    private val caseToViewAppointments: CaseToViewAppointments,
    private val caseToLogout: CaseToLogout,
    private val caseToClearScreenCache: CaseToClearScreenCache
) {

    suspend fun viewAppointments(selectedId: String?, processId: String): PresAppointmentScreenViewState =
        caseToViewAppointments.launch(selectedId, processId).toPres()

    suspend fun logoutUser(){
        caseToLogout.launch()
    }

    fun clearCache(processId: String) {
        caseToClearScreenCache.launch(processId)
    }

}