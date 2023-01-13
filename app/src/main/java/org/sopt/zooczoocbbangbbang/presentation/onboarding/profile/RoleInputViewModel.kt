package org.sopt.zooczoocbbangbbang.presentation.onboarding.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoleInputViewModel : ViewModel() {
    val roleTextStream: MutableLiveData<String> = MutableLiveData("")

    private val _isEnableButtonStream: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEnableButtonStream: LiveData<Boolean>
        get() = _isEnableButtonStream

    fun enableRegisterRoleButton() {
        _isEnableButtonStream.value = true
    }

    fun disableRegisterRoleButton() {
        _isEnableButtonStream.value = false
    }
}
