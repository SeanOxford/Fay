package com.oxford.sean.pres.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxford.sean.presia.controller.LoginScreenController
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor(private val controller: LoginScreenController) : ViewModel() {

    fun onLoginClicked(username: String, password: String) {
        viewModelScope.launch {
            controller.login(username, password)
        }
    }

}