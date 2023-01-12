package org.sopt.zooczoocbbangbbang.presentation.onboarding

import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.util.NonNullLiveData
import org.sopt.zooczoocbbangbbang.util.NonNullMutableLiveData

class OnboardingViewModel : ViewModel() {
    private val _onboardingStream: NonNullMutableLiveData<Onboarding> =
        NonNullMutableLiveData(Onboarding.INPUT_ROLE)
    val onboardingStream: NonNullLiveData<Onboarding>
        get() = _onboardingStream

    fun moveNextStepOnboarding(callback: () -> Unit = {}) {
        callback()
        _onboardingStream.value.next()?.let { _onboardingStream.value = it }
    }

    fun movePreviousStepOnboarding() {
        _onboardingStream.value.previous()?.let { _onboardingStream.value = it }
    }
}
