package com.oxford.sean.presia.controller

import com.oxford.sean.domain.usecase.CaseToNavigateLogin
import javax.inject.Inject


class AppIntroController @Inject constructor(private val caseToNavigateLogin: CaseToNavigateLogin) {

    suspend fun goToLogin() {
        caseToNavigateLogin.launch()
    }

}