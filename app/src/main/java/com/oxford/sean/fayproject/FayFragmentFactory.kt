package com.oxford.sean.fayproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.oxford.sean.pres.screen.appointments.AppointmentsFragment
import com.oxford.sean.pres.screen.appintro.AppIntroFragment
import com.oxford.sean.pres.screen.login.LoginFragment
import javax.inject.Inject

class FayFragmentFactory @Inject constructor(private val viewModelFactory: ViewModelProvider.Factory) :
    FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        when (className) {
            AppIntroFragment::class.java.name -> return AppIntroFragment(viewModelFactory)
            LoginFragment::class.java.name -> return LoginFragment(viewModelFactory)
            AppointmentsFragment::class.java.name -> return AppointmentsFragment(viewModelFactory)
        }
        return super.instantiate(classLoader, className)
    }
}