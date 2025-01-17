package com.oxford.sean.pres.screen.appointments.view

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.oxford.sean.pres.R
import com.oxford.sean.pres.dpToPx
import com.oxford.sean.pres.screen.appointments.AppointmentsRecyclerViewAdapter
import com.oxford.sean.pres.widgets.EvenSpacingRecyclerViewDecorator
import com.oxford.sean.presia.model.PresAppointmentTab

class AppointmentsPagerView(context: Context, val tabId: Int, onAppointmentSelected: (String) -> Unit) :
    FrameLayout(context) {

    private val adapter = AppointmentsRecyclerViewAdapter(onAppointmentSelected)

    fun updateTab(tab: PresAppointmentTab) {
        adapter.submitList(tab.appointmentRows)
    }

    init {
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        inflate(context, R.layout.view_appointments_tab, this)

        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView_appointments_tab_appointments)

        recyclerView.addItemDecoration(EvenSpacingRecyclerViewDecorator(16.dpToPx))
        recyclerView.adapter = adapter
    }
}