package com.oxford.sean.presia.model

data class PresAppointmentScreenViewState(
    val title: String,
    val tabs: List<PresAppointmentTab>
)

data class PresAppointmentTab(
    val id: Int,
    val title: String,
    val appointmentRows: List<PresAppointmentRow>
)

data class PresAppointmentRow(
    val id: String,
    val monthText: String,
    val dateText: String,
    val titleText: String,
    val timeText: String,
    val recurrenceText: String,
    val showRecurrenceIcon: Boolean,
    val showVideoCta: Boolean
)