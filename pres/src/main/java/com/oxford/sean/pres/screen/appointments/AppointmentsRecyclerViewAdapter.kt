package com.oxford.sean.pres.screen.appointments

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oxford.sean.pres.screen.appointments.view.AppointmentRowView
import com.oxford.sean.presia.model.PresAppointmentRow

class AppointmentsRecyclerViewAdapter(
    private val onAppointmentSelected: (String) -> Unit
) : RecyclerView.Adapter<AppointmentRowViewHolder>() {

    private var rows: List<PresAppointmentRow> = emptyList()

    fun submitList(rows: List<PresAppointmentRow>) {
        val diffCallback = DiffUtilCallback(this.rows, rows)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.rows = rows
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentRowViewHolder =
        AppointmentRowViewHolder(AppointmentRowView(parent.context, onAppointmentSelected))

    override fun getItemCount(): Int = rows.size

    override fun onBindViewHolder(holder: AppointmentRowViewHolder, position: Int) {
        holder.view.bind(rows[position])
    }
}

class AppointmentRowViewHolder(val view: AppointmentRowView) : RecyclerView.ViewHolder(view)

private class DiffUtilCallback(
    private val oldRows: List<PresAppointmentRow>,
    private val newRows: List<PresAppointmentRow>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldRows.size
    override fun getNewListSize(): Int = newRows.size
    override fun areItemsTheSame(oldPos: Int, newPos: Int) = compareItems(oldRows[oldPos], newRows[newPos])
    override fun areContentsTheSame(oldPos: Int, newPos: Int) = compareContents(oldRows[oldPos], newRows[newPos])

    private fun compareItems(old: PresAppointmentRow, new: PresAppointmentRow): Boolean {
        return old.id == new.id
    }

    private fun compareContents(old: PresAppointmentRow, new: PresAppointmentRow): Boolean {
        return old == new
    }
}

