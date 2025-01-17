package com.oxford.sean.fayproject.di.module

import android.accounts.AccountManager
import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AccountModule {

    @Provides
    @Singleton
    fun accountManager(application: Application): AccountManager = AccountManager.get(application)
}