package com.oxford.sean.pres.screen.appointments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oxford.sean.presia.controller.AppointmentsScreenController
import com.oxford.sean.presia.model.PresAppointmentScreenViewState
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppointmentsFragmentViewModel @Inject constructor(private val controller: AppointmentsScreenController) :
    ViewModel() {

    private val viewModelInstanceId = this.toString()

    private val _viewStateLiveData = MutableLiveData<PresAppointmentScreenViewState>()
    val viewStateLiveData: LiveData<PresAppointmentScreenViewState> = _viewStateLiveData

    private var selectJob: Job = Job()

    fun onCreateView() {
        viewModelScope.launch {
            val viewState = controller.viewAppointments(selectedId = null, processId = viewModelInstanceId)
            withContext(Main) {
                _viewStateLiveData.value = viewState
            }
        }
    }

    fun onAppointmentSelected(selectedId: String?) {
        selectJob.cancel()
        selectJob = viewModelScope.launch {
            val viewState = controller.viewAppointments(selectedId, processId = viewModelInstanceId)
            withContext(Main) {
                _viewStateLiveData.value = viewState
            }
        }
    }

    fun onLogoutClicked() {
        viewModelScope.launch {
            controller.logoutUser()
        }
    }

    override fun onCleared() {
        controller.clearCache(processId = viewModelInstanceId)
    }

}


