package com.oxford.sean.presia.controller

import com.oxford.sean.domain.usecase.CaseToLogin
import javax.inject.Inject


class LoginScreenController @Inject constructor(private val caseToLogin: CaseToLogin) {

    suspend fun login(username: String, password: String){
        caseToLogin.launch(username, password)
    }

}