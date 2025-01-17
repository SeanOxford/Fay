package com.oxford.sean.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.entity.AppointmentEntity
import com.oxford.sean.domain.entity.AppointmentRecurrenceTypeEntity
import com.oxford.sean.domain.entity.AppointmentStatusEntity
import com.oxford.sean.domain.entity.AppointmentTypeEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CaseToViewAppointmentsTest {

    private lateinit var dataGateway: DataGateway
    private lateinit var caseToViewAppointments: CaseToViewAppointments

    @Before
    fun setup() {
        dataGateway = mockk()
        caseToViewAppointments = CaseToViewAppointments(dataGateway)
    }

    @Test
    fun launch_hasUpcomingAndPastAppointments_hasSplitTabs() = runTest {
        val currentDate = Date()
        val pastAppointment = AppointmentEntity(
            id = "1",
            patientId = 123L,
            providerId = 456L,
            status = AppointmentStatusEntity.OCCURRED,
            appointmentType = AppointmentTypeEntity.FOLLOW_UP,
            start = Date(currentDate.time - 3600_000),
            end = Date(currentDate.time - 1800_000),
            durationMins = 30,
            recurrenceType = AppointmentRecurrenceTypeEntity.UNKNOWN
        )

        val upcomingAppointment = AppointmentEntity(
            id = "2",
            patientId = 123L,
            providerId = 456L,
            status = AppointmentStatusEntity.OCCURRED,
            appointmentType = AppointmentTypeEntity.INITIAL_CONSULT,
            start = Date(currentDate.time + 3600_000),
            end = Date(currentDate.time + 5400_000),
            durationMins = 30,
            recurrenceType = AppointmentRecurrenceTypeEntity.WEEKLY
        )

        coEvery { dataGateway.getAppointments("processId123") } returns listOf(
            pastAppointment,
            upcomingAppointment
        )

        val result = caseToViewAppointments.launch(selectedId = null, processId = "processId123")

        assertThat(result.title).isEqualTo("Appointments")
        assertThat(result.tabs).hasSize(2)

        val upcomingTab = result.tabs.first { it.id == 0 }
        assertThat(upcomingTab.appointments).hasSize(1)
        assertThat(upcomingTab.appointments[0].id).isEqualTo("2")
        assertThat(upcomingTab.appointments[0].showVideoCta).isFalse()

        val pastTab = result.tabs.first { it.id == 1 }
        assertThat(pastTab.appointments).hasSize(1)
        assertThat(pastTab.appointments[0].id).isEqualTo("1")
        assertThat(pastTab.appointments[0].showVideoCta).isFalse()
    }

    @Test
    fun launch_hasSelectedAppointment_showVideoCta() = runTest {

        val currentDate = Date()
        val pastAppointment = AppointmentEntity(
            id = "1",
            patientId = 123L,
            providerId = 456L,
            status = AppointmentStatusEntity.OCCURRED,
            appointmentType = AppointmentTypeEntity.FOLLOW_UP,
            start = Date(currentDate.time - 3600_000),
            end = Date(currentDate.time - 1800_000),
            durationMins = 30,
            recurrenceType = AppointmentRecurrenceTypeEntity.UNKNOWN
        )

        coEvery { dataGateway.getAppointments("processId123") } returns listOf(pastAppointment)

        val result = caseToViewAppointments.launch(selectedId = "1", processId = "processId123")

        assertThat(result.title).isEqualTo("Appointments")
        assertThat(result.tabs).hasSize(2)

        val pastTab = result.tabs.first { it.id == 1 }
        assertThat(pastTab.appointments).hasSize(1)
        assertThat(pastTab.appointments[0].id).isEqualTo("1")
        assertThat(pastTab.appointments[0].showVideoCta).isTrue()
    }

    @Test
    fun launch_withNoAppointments_returnsEmptyTabs() = runTest {
        coEvery { dataGateway.getAppointments("processId123") } returns emptyList()

        val result = caseToViewAppointments.launch(selectedId = null, processId = "processId123")

        assertThat(result.title).isEqualTo("Appointments")
        assertThat(result.tabs).hasSize(2)

        val upcomingTab = result.tabs.first { it.id == 0 }
        assertThat(upcomingTab.appointments).isEmpty()

        val pastTab = result.tabs.first { it.id == 1 }
        assertThat(pastTab.appointments).isEmpty()
    }

    @Test
    fun launch_dateInput_correctTimeAndTitleText() = runTest {
        val weeklyAppointment = AppointmentEntity(
            id = "1",
            patientId = 123L,
            providerId = 456L,
            status = AppointmentStatusEntity.OCCURRED,
            appointmentType = AppointmentTypeEntity.FOLLOW_UP,
            start = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("2026-01-01 10:00:00")!!,
            end = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("2026-01-01 11:00:00")!!,
            durationMins = 60,
            recurrenceType = AppointmentRecurrenceTypeEntity.WEEKLY
        )

        coEvery { dataGateway.getAppointments(any()) } returns listOf(weeklyAppointment)

        val result = caseToViewAppointments.launch(selectedId = "1", processId = "process123")

        val upcomingTab = result.tabs.first { it.id == 0 }
        val row = upcomingTab.appointments[0]
        assertThat(row.monthText).isEqualTo("JAN")
        assertThat(row.dateText).isEqualTo("01")
        assertThat(row.titleText).isEqualTo("Follow up with Taylor Palmer, MD")
        assertThat(row.timeText).contains("10:00 AM - 11:00 AM")
    }

    @Test
    fun launch_weeklyRecurrenceAppointment_showIconTextWeekly() = runTest {
        val weeklyAppointment = AppointmentEntity(
            id = "1",
            patientId = 123L,
            providerId = 456L,
            status = AppointmentStatusEntity.OCCURRED,
            appointmentType = AppointmentTypeEntity.FOLLOW_UP,
            start = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("2026-01-01 10:00:00")!!,
            end = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("2026-01-01 11:00:00")!!,
            durationMins = 60,
            recurrenceType = AppointmentRecurrenceTypeEntity.WEEKLY
        )

        coEvery { dataGateway.getAppointments(any()) } returns listOf(weeklyAppointment)

        val result = caseToViewAppointments.launch(selectedId = "1", processId = "process123")

        val upcomingTab = result.tabs.first { it.id == 0 }
        val row = upcomingTab.appointments[0]
        assertThat(row.recurrenceText).isEqualTo("Weekly")
        assertThat(row.shouldShowRecurrenceIcon).isTrue()
        assertThat(row.showVideoCta).isTrue()
    }

    @Test
    fun launch_unknownRecurrenceAppointment_dontShowIcon() = runTest {
        val unknownRecurrenceAppointment = AppointmentEntity(
            id = "2",
            patientId = 123L,
            providerId = 456L,
            status = AppointmentStatusEntity.OCCURRED,
            appointmentType = AppointmentTypeEntity.UNKNOWN,
            start = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("2025-01-01 12:00:00")!!,
            end = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("2025-01-01 12:30:00")!!,
            durationMins = 30,
            recurrenceType = AppointmentRecurrenceTypeEntity.UNKNOWN
        )

        coEvery { dataGateway.getAppointments(any()) } returns listOf(unknownRecurrenceAppointment)

        val result = caseToViewAppointments.launch(selectedId = "3", processId = "process123")

        val pastTab = result.tabs.first { it.id == 1 }
        assertThat(pastTab.appointments).hasSize(1)
        val row = pastTab.appointments[0]
        assertThat(row.id).isEqualTo("2")
        assertThat(row.recurrenceText).isEmpty()
        assertThat(row.shouldShowRecurrenceIcon).isFalse()
    }

}