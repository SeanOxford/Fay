package com.oxford.sean.pres.screen.appointments.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.oxford.sean.pres.R
import com.oxford.sean.pres.clipRoundCorners

class AppointmentDateSquareView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val month: TextView
    private val date: TextView

    init {
        inflate(context, R.layout.view_appointment_date_square, this)
        this.clipRoundCorners()

        month = findViewById(R.id.TextView_appointment_date_square_month)
        date = findViewById(R.id.TextView_appointment_date_square_date)
    }

    fun setMonthText(monthText: String) {
        month.text = monthText
    }

    fun setDateText(dateText: String) {
        date.text = dateText
    }

}