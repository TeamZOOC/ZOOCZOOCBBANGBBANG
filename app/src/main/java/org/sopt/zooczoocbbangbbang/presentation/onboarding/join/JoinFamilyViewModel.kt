package org.sopt.zooczoocbbangbbang.presentation.onboarding.join

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JoinFamilyViewModel : ViewModel() {
    val codeInputStream: MutableLiveData<String> = MutableLiveData("")

    private val _isEnableButtonStream: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEnableButtonStream: LiveData<Boolean> get() = _isEnableButtonStream

    fun enableJoinFamilyButton() {
        _isEnableButtonStream.value = true
    }

    fun disableJoinFamilyButton() {
        _isEnableButtonStream.value = false
    }
}
