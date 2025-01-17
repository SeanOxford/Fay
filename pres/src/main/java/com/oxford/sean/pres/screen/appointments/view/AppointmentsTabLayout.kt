package com.oxford.sean.pres.screen.appointments.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.oxford.sean.pres.R
import com.oxford.sean.pres.getColorByRes

class AppointmentsTabLayout(context: Context, attrs: AttributeSet) : TabLayout(context, attrs), OnTabSelectedListener {

    init {
        addOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: Tab?) {
        (tab?.customView as? TextView)?.setTextColor(context.getColorByRes(R.color.brand_color))
    }

    override fun onTabUnselected(tab: Tab?) {
        (tab?.customView as? TextView)?.setTextColor(context.getColorByRes(R.color.text_secondary))
    }

    override fun onTabReselected(tab: Tab?) = Unit
}