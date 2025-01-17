package com.oxford.sean.dataia.transformer

import com.oxford.sean.dataia.model.ApiAppointment
import com.oxford.sean.domain.entity.AppointmentEntity
import com.oxford.sean.domain.entity.AppointmentRecurrenceTypeEntity
import com.oxford.sean.domain.entity.AppointmentStatusEntity
import com.oxford.sean.domain.entity.AppointmentTypeEntity

fun ApiAppointment.toEntity(): AppointmentEntity =
    AppointmentEntity(
        id = id,
        patientId = patientId,
        providerId = providerId,
        status = status.toAppointmentStatus(),
        appointmentType = appointmentType.toAppointmentType(),
        start = start,
        end = end,
        durationMins = durationMins,
        recurrenceType = recurrenceType.toRecurrenceType()
    )

private fun String.toAppointmentStatus() = when (this) {
    "Scheduled" -> AppointmentStatusEntity.SCHEDULED
    "Occurred" -> AppointmentStatusEntity.OCCURRED
    else -> AppointmentStatusEntity.UNKNOWN
}

private fun String.toAppointmentType() = when (this) {
    "Follow-up" -> AppointmentTypeEntity.FOLLOW_UP
    "Initial consultation" -> AppointmentTypeEntity.INITIAL_CONSULT
    else -> AppointmentTypeEntity.UNKNOWN
}

private fun String.toRecurrenceType() = when (this) {
    "Weekly" -> AppointmentRecurrenceTypeEntity.WEEKLY
    else -> AppointmentRecurrenceTypeEntity.UNKNOWN
}