package com.oxford.sean.dataia.model

import com.oxford.sean.dataia.serialization.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ApiAppointment(
    @SerialName("appointment_id") val id: String,
    @SerialName("patient_id") val patientId: Long,
    @SerialName("provider_id") val providerId: Long,
    @SerialName("status") val status: String,
    @SerialName("appointment_type") val appointmentType: String,
    @Serializable(with = DateSerializer::class)
    @SerialName("start") val start: Date,
    @Serializable(with = DateSerializer::class)
    @SerialName("end") val end: Date,
    @SerialName("duration_in_minutes") val durationMins: Int,
    @SerialName("recurrence_type") val recurrenceType: String
)

