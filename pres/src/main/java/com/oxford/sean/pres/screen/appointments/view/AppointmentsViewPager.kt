package com.oxford.sean.pres.screen.appointments.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.oxford.sean.pres.R
import com.oxford.sean.presia.model.PresAppointmentTab

class AppointmentsViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    private var tabViews: List<AppointmentsPagerView>? = null

    fun setupTabs(
        tabLayout: AppointmentsTabLayout,
        tabs: List<PresAppointmentTab>,
        onAppointmentSelected: (String) -> Unit
    ) {
        val pagerViews = tabs.map { AppointmentsPagerView(context, it.id, onAppointmentSelected) }
        adapter = AppointmentsViewPagerAdapter(pagerViews)
        this.tabViews = pagerViews
        tabLayout.setupWithViewPager(this)
        initTabText(tabLayout, tabs.map { it.title })
    }

    fun submitData(tabs: List<PresAppointmentTab>) {
        tabs.forEach { tabData -> tabViews?.firstOrNull { it.tabId == tabData.id }?.updateTab(tabData) }
    }

    private fun initTabText(tabs: AppointmentsTabLayout, titles: List<String>) {
        for (i in 0 until tabs.tabCount) {
            val tab = tabs.getTabAt(i)
            tab?.setCustomView(R.layout.view_appointments_tab_title)
            tab?.text = titles[i]
            if (i == 0) {
                tabs.onTabSelected(tabs.getTabAt(i))
            } else {
                tabs.onTabUnselected(tabs.getTabAt(i))
            }
        }
    }
}

private class AppointmentsViewPagerAdapter(private val pagerViews: List<AppointmentsPagerView>) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): View {
        val homeView = pagerViews[position]
        collection.addView(homeView)
        return homeView
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun getCount(): Int = pagerViews.size

    override fun isViewFromObject(view: View, o: Any): Boolean = view === o
}
