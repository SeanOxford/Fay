package com.oxford.sean.dataia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiAppointmentsResponse(@SerialName("appointments") val appointments: List<ApiAppointment>)