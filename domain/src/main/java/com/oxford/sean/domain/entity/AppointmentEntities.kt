package com.oxford.sean.domain.entity

import java.util.Date

data class AppointmentEntity(
    val id: String,
    val patientId: Long,
    val providerId: Long,
    val status: AppointmentStatusEntity,
    val appointmentType: AppointmentTypeEntity,
    val start: Date,
    val end: Date,
    val durationMins: Int,
    val recurrenceType: AppointmentRecurrenceTypeEntity
)

data class AppointmentScreenViewStateEntity(
    val title: String,
    val tabs: List<AppointmentTabEntity>
)

data class AppointmentTabEntity(
    val id: Int,
    val title: String,
    val appointments: List<AppointmentRowEntity>
)

data class AppointmentRowEntity(
    val id: String,
    val monthText: String,
    val dateText: String,
    val titleText: String,
    val timeText: String,
    val recurrenceText: String,
    val shouldShowRecurrenceIcon: Boolean,
    val showVideoCta: Boolean
)

enum class AppointmentStatusEntity { SCHEDULED, OCCURRED, UNKNOWN }
enum class AppointmentTypeEntity { FOLLOW_UP, INITIAL_CONSULT, UNKNOWN }
enum class AppointmentRecurrenceTypeEntity { WEEKLY, UNKNOWN }