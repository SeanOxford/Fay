package com.oxford.sean.fayproject.di

import android.app.Application
import com.oxford.sean.fayproject.FayNavHostFragment
import com.oxford.sean.fayproject.MainActivity
import com.oxford.sean.fayproject.di.module.AccountModule
import com.oxford.sean.fayproject.di.module.DataRepoModule
import com.oxford.sean.fayproject.di.module.DomainModule
import com.oxford.sean.fayproject.di.module.FragmentFactoryModule
import com.oxford.sean.fayproject.di.module.NavModule
import com.oxford.sean.fayproject.di.module.NetworkModule
import com.oxford.sean.fayproject.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FragmentFactoryModule::class, ViewModelModule::class, NetworkModule::class, DomainModule::class,
    DataRepoModule::class, NavModule::class, AccountModule::class])
interface AppComponent {

    fun inject(navHostFragment: FayNavHostFragment)
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}