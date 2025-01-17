package com.oxford.sean.fayproject.di.module

import com.oxford.sean.dataia.DataGatewayImpl
import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.PresGateway
import com.oxford.sean.presia.PresGatewayImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun provideDataGateway(dataGateway: DataGatewayImpl): DataGateway

    @Singleton
    @Binds
    abstract fun providePresGateway(presGateway: PresGatewayImpl): PresGateway

}