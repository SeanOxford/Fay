package com.oxford.sean.presia

interface WindowNavigator {

    suspend fun showLoadingDialog()

    suspend fun hideLoadingDialog()
}