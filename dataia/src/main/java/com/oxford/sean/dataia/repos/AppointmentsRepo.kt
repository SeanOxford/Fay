package com.oxford.sean.dataia.repos

import com.oxford.sean.dataia.model.ApiAppointment

interface AppointmentsRepo {

    suspend fun getAppointments(processId: String?) : List<ApiAppointment>

    fun invalidateProcessCache(processId: String)

}