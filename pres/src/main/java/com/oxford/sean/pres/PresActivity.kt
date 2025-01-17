package com.oxford.sean.pres

import androidx.navigation.NavController

interface PresActivity {

    val navController: NavController

    fun showLoadingDialog()

    fun hideLoadingDialog()
}