package com.oxford.sean.presia.controller

import com.oxford.sean.domain.usecase.CaseToInitActivity
import javax.inject.Inject

class MainActivityController @Inject constructor(private val caseToInitActivity: CaseToInitActivity) {

    suspend fun initActivity() {
        caseToInitActivity.launch()
    }

}