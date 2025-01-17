package com.oxford.sean.fayproject

import android.app.Activity
import android.app.Application
import com.oxford.sean.fayproject.di.AppComponent
import com.oxford.sean.fayproject.di.DaggerAppComponent
import com.oxford.sean.pres.PresApplication

class FayApp : Application(), PresApplication {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var sApplication: FayApp
    }

    // This is a single-activity app, if rotation destroys the activity, this will be reassigned
    // and the previous one will not be leaked
    var mainActivity: Activity? = null

    override fun onCreate() {
        sApplication = this
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getActivity(): Activity? = mainActivity


}