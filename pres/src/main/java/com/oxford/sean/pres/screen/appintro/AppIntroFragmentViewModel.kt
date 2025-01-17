package com.oxford.sean.pres.screen.appintro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxford.sean.presia.controller.AppIntroController
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppIntroFragmentViewModel @Inject constructor(private val controller: AppIntroController) : ViewModel() {

    fun onLoginClicked() {
        viewModelScope.launch { controller.goToLogin() }
    }

}