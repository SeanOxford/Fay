package com.oxford.sean.fayproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxford.sean.presia.controller.MainActivityController
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val controller: MainActivityController) : ViewModel() {

    fun onCreate() {
        viewModelScope.launch { controller.initActivity() }
    }

}
