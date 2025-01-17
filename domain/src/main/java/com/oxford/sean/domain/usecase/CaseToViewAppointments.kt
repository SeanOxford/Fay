package com.oxford.sean.domain.usecase

import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.entity.AppointmentEntity
import com.oxford.sean.domain.entity.AppointmentRecurrenceTypeEntity
import com.oxford.sean.domain.entity.AppointmentRowEntity
import com.oxford.sean.domain.entity.AppointmentScreenViewStateEntity
import com.oxford.sean.domain.entity.AppointmentTabEntity
import com.oxford.sean.domain.entity.AppointmentTypeEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class CaseToViewAppointments @Inject constructor(private val dataGateway: DataGateway) {

    private val monthDateFormat = SimpleDateFormat("MMM", Locale.getDefault())
    private val dateDateFormat = SimpleDateFormat("dd", Locale.getDefault())
    private val timeDateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    private val timeZoneDateFormat = SimpleDateFormat("z", Locale.getDefault()).apply { timeZone = TimeZone.getDefault() }

    suspend fun launch(selectedId: String?, processId: String): AppointmentScreenViewStateEntity {
        val appointments = dataGateway.getAppointments(processId)

        val currentDate = Date()
        val upcomingAppointmentRows =
            appointments.filter { it.start.after(currentDate) }.map { it.toRowViewEntity(selectedId) }
        val pastAppointmentRows =
            appointments.filter { it.start.before(currentDate) }.map { it.toRowViewEntity(selectedId) }

        val upcomingAppointmentsTab = AppointmentTabEntity(id = 0, title = "Upcoming", upcomingAppointmentRows)
        val previousAppointmentsTab = AppointmentTabEntity(id = 1, title = "Past", pastAppointmentRows)

        return AppointmentScreenViewStateEntity(
            title = "Appointments",
            listOf(upcomingAppointmentsTab, previousAppointmentsTab)
        )
    }

    private fun AppointmentEntity.toRowViewEntity(selectedId: String?): AppointmentRowEntity {
        val monthText = monthDateFormat.format(this.start).uppercase()
        val dateText = dateDateFormat.format(this.start)

        val timeStartString = timeDateFormat.format(this.start)
        val timeEndString = timeDateFormat.format(this.end)
        val timeZoneText = timeZoneDateFormat.format(Date())

        val recurrenceText = when (this.recurrenceType) {
            AppointmentRecurrenceTypeEntity.WEEKLY -> "Weekly"
            AppointmentRecurrenceTypeEntity.UNKNOWN -> ""
        }

        val doctorName = "Taylor Palmer, MD"

        val titleText = when (this.appointmentType) {
            AppointmentTypeEntity.FOLLOW_UP -> "Follow up with $doctorName"
            AppointmentTypeEntity.INITIAL_CONSULT -> "Start your initial consult with $doctorName"
            AppointmentTypeEntity.UNKNOWN -> ""
        }

        val row = AppointmentRowEntity(
            id = this.id,
            monthText = monthText,
            dateText = dateText,
            titleText = titleText,
            timeText = "$timeStartString - $timeEndString ($timeZoneText)",
            recurrenceText = recurrenceText,
            shouldShowRecurrenceIcon = this.recurrenceType == AppointmentRecurrenceTypeEntity.WEEKLY,
            showVideoCta = this.id == selectedId,
        )

        return row
    }

}