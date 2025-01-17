package com.oxford.sean.dataia

import com.oxford.sean.dataia.repos.AppointmentsRepo
import com.oxford.sean.dataia.repos.AccountRepo
import com.oxford.sean.dataia.transformer.toEntity
import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.entity.AppointmentEntity
import javax.inject.Inject

class DataGatewayImpl @Inject constructor(
    private val accountRepo: AccountRepo,
    private val appointmentsRepo: AppointmentsRepo
) : DataGateway {

    override suspend fun login(username: String, password: String) {
        accountRepo.login(username, password)
    }

    override suspend fun logout() {
        accountRepo.logout()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return accountRepo.isUserLoggedIn()
    }

    override suspend fun getAppointments(processId: String?): List<AppointmentEntity> {
        return appointmentsRepo.getAppointments(processId).map { it.toEntity() }
    }

    override fun invalidateProcesses(processId: String) {
        appointmentsRepo.invalidateProcessCache(processId)
    }

}