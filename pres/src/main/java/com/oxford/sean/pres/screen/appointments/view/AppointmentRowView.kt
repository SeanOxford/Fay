package com.oxford.sean.pres.screen.appointments.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.oxford.sean.pres.R
import com.oxford.sean.pres.clipRoundCorners
import com.oxford.sean.pres.dpToPxF
import com.oxford.sean.pres.setRoundCornerBackground
import com.oxford.sean.pres.show
import com.oxford.sean.presia.model.PresAppointmentRow

@SuppressLint("ViewConstructor")
class AppointmentRowView(
    context: Context,
    private val onAppointmentSelected: (String) -> Unit
) : ConstraintLayout(context) {

    private val dateSquare: AppointmentDateSquareView
    private val title: TextView
    private val time: TextView
    private val recurrence: TextView
    private val recurrenceIcon: ImageView
    private val cta: Button

    init {
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        inflate(context, R.layout.view_appointment_row, this)

        dateSquare = findViewById(R.id.AppointmentDateSquareView_row_date_square)
        title = findViewById(R.id.TextView_appointment_row_title)
        time = findViewById(R.id.TextView_appointment_row_time)
        recurrence = findViewById(R.id.TextView_appointment_row_recurrence)
        recurrenceIcon = findViewById(R.id.TextView_appointment_row_recurrence_icon)
        cta = findViewById(R.id.Button_appointment_row_cta)
        val container = findViewById<ConstraintLayout>(R.id.ConstraintLayout_appointment_row_container)

        container.setRoundCornerBackground(R.color.transparent, R.color.border_light, cornerRadius = 24.dpToPxF)
        cta.clipRoundCorners()
    }

    fun bind(data: PresAppointmentRow) {
        dateSquare.setMonthText(data.monthText)
        dateSquare.setDateText(data.dateText)
        title.text = data.titleText
        time.text = data.timeText
        recurrence.text = data.recurrenceText
        recurrence.show(data.showRecurrenceIcon)
        recurrenceIcon.show(data.showRecurrenceIcon)
        cta.show(data.showVideoCta)

        setOnClickListener { onAppointmentSelected.invoke(data.id) }
    }
}