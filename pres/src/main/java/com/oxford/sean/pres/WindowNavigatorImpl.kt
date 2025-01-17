package com.oxford.sean.pres

import android.app.Application
import com.oxford.sean.presia.WindowNavigator
import javax.inject.Inject

class WindowNavigatorImpl @Inject constructor(private val application: Application) : WindowNavigator {
    override suspend fun showLoadingDialog() {
        val activity = (application as PresApplication).getActivity()
        (activity as? PresActivity)?.showLoadingDialog()
    }

    override suspend fun hideLoadingDialog() {
        val activity = (application as PresApplication).getActivity()
        (activity as? PresActivity)?.hideLoadingDialog()
    }

}