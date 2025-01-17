package com.oxford.sean.pres.screen.appointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.oxford.sean.pres.R
import com.oxford.sean.pres.fadeOut
import com.oxford.sean.pres.screen.appointments.view.AppointmentsTabLayout
import com.oxford.sean.pres.screen.appointments.view.AppointmentsViewPager
import com.oxford.sean.pres.view.LoadingCoverView

class AppointmentsFragment(private val viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private val viewModel: AppointmentsFragmentViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_appointments, null)

        val title = v.findViewById<TextView>(R.id.TextView_appointments_fragment_title)
        val logoutButton = v.findViewById<TextView>(R.id.TextView_appointments_fragment_logout)
        val tabsLayout = v.findViewById<AppointmentsTabLayout>(R.id.AppointmentsTabLayout_appointments_fragment_tabs)
        val viewPager = v.findViewById<AppointmentsViewPager>(R.id.AppointmentsViewPager_appointments_fragments_pager)
        val loadingCover = v.findViewById<LoadingCoverView>(R.id.LoadingCoverView_appointments_fragment_cover)

        logoutButton.setOnClickListener { viewModel.onLogoutClicked() }

        viewModel.viewStateLiveData.observe(viewLifecycleOwner) { viewState ->
            title.text = viewState.title

            val hasViewPagerInit = viewPager.adapter == null
            if (hasViewPagerInit) {
                viewPager.setupTabs(
                    tabsLayout,
                    viewState.tabs,
                    onAppointmentSelected = { viewModel.onAppointmentSelected(it) })
            }

            viewPager.submitData(viewState.tabs)
            loadingCover.fadeOut(duration = 400)
        }

        viewModel.onCreateView()

        return v
    }

}

