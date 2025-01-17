package com.oxford.sean.fayproject.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oxford.sean.fayproject.FayViewModelFactory
import com.oxford.sean.fayproject.MainActivityViewModel
import com.oxford.sean.fayproject.di.ViewModelKey
import com.oxford.sean.pres.screen.appintro.AppIntroFragmentViewModel
import com.oxford.sean.pres.screen.appointments.AppointmentsFragmentViewModel
import com.oxford.sean.pres.screen.login.LoginFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: FayViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppIntroFragmentViewModel::class)
    abstract fun bindAppIntroFragmentViewModel(appIntroFragmentViewModel: AppIntroFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun bindLoginFragmentViewModel(loginFragmentViewModel: LoginFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppointmentsFragmentViewModel::class)
    abstract fun bindAppointmentsFragmentViewModel(appointmentsFragmentViewModel: AppointmentsFragmentViewModel): ViewModel

}