package com.oxford.sean.presia.transformer

import com.oxford.sean.domain.entity.AppointmentRowEntity
import com.oxford.sean.domain.entity.AppointmentScreenViewStateEntity
import com.oxford.sean.domain.entity.AppointmentTabEntity
import com.oxford.sean.presia.model.PresAppointmentRow
import com.oxford.sean.presia.model.PresAppointmentScreenViewState
import com.oxford.sean.presia.model.PresAppointmentTab


fun AppointmentRowEntity.toPres() = PresAppointmentRow(
    id = id,
    monthText = monthText,
    dateText = dateText,
    titleText = titleText,
    timeText = timeText,
    recurrenceText = recurrenceText,
    showRecurrenceIcon = shouldShowRecurrenceIcon,
    showVideoCta = showVideoCta
)

fun AppointmentTabEntity.toPres() =
    PresAppointmentTab(id = id, title = title, appointmentRows = appointments.map { it.toPres() })

fun AppointmentScreenViewStateEntity.toPres() =
    PresAppointmentScreenViewState(title = title, tabs = tabs.map { it.toPres() })