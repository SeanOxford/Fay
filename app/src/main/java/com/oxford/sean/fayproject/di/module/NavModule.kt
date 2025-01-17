package com.oxford.sean.fayproject.di.module

import com.oxford.sean.pres.ScreenNavigatorImpl
import com.oxford.sean.pres.ToastNavigatorImpl
import com.oxford.sean.pres.WindowNavigatorImpl
import com.oxford.sean.presia.ScreenNavigator
import com.oxford.sean.presia.ToastNavigator
import com.oxford.sean.presia.WindowNavigator
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class NavModule {

    @Singleton
    @Binds
    abstract fun provideScreenNavigator(screenNavigator: ScreenNavigatorImpl): ScreenNavigator

    @Singleton
    @Binds
    abstract fun provideWindowNavigator(windowNavigator: WindowNavigatorImpl): WindowNavigator

    @Singleton
    @Binds
    abstract fun provideToastNavigator(toastNavigator: ToastNavigatorImpl): ToastNavigator

}