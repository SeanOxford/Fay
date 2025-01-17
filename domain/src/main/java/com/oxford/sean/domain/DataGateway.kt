package com.oxford.sean.domain

import com.oxford.sean.domain.entity.AppointmentEntity

interface DataGateway {

    suspend fun login(username: String, password: String)

    suspend fun logout()

    suspend fun isUserLoggedIn(): Boolean

    suspend fun getAppointments(processId: String?): List<AppointmentEntity>

    fun invalidateProcesses(processId: String)

}