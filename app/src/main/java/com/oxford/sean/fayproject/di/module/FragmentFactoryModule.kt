package com.oxford.sean.fayproject.di.module

import androidx.fragment.app.FragmentFactory
import com.oxford.sean.fayproject.FayFragmentFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FragmentFactoryModule {

    @Binds
    abstract fun provideAppFragmentFactory(factory: FayFragmentFactory): FragmentFactory

}