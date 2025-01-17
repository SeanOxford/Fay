package com.oxford.sean.fayproject.di.module

import com.oxford.sean.data.repo.AccountRepoImpl
import com.oxford.sean.data.repo.AppointmentsRepoImpl
import com.oxford.sean.dataia.repos.AccountRepo
import com.oxford.sean.dataia.repos.AppointmentsRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataRepoModule {

    @Singleton
    @Binds
    abstract fun providesAccountRepo(repo: AccountRepoImpl): AccountRepo

    @Singleton
    @Binds
    abstract fun providesAppointmentsRepo(repo: AppointmentsRepoImpl): AppointmentsRepo

}