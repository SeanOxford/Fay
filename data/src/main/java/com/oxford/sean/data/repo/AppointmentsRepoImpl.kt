package com.oxford.sean.data.repo

import com.oxford.sean.data.ApiSource
import com.oxford.sean.dataia.model.ApiAppointment
import com.oxford.sean.dataia.repos.AppointmentsRepo
import javax.inject.Inject

class AppointmentsRepoImpl @Inject constructor(private val apiSource: ApiSource) : AppointmentsRepo {

    private val cachedAppointments = mutableMapOf<String, List<ApiAppointment>>()

    override suspend fun getAppointments(processId: String?): List<ApiAppointment> {
        if (cachedAppointments.contains(processId)) return cachedAppointments[processId]!!

        val appointments = apiSource.getAppointments().appointments

        processId?.let { cachedAppointments[it] = appointments }

        return appointments
    }

    override fun invalidateProcessCache(processId: String) {
        cachedAppointments.remove(processId)
    }

}