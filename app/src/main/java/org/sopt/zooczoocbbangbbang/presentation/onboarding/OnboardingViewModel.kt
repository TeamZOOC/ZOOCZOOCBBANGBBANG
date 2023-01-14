package org.sopt.zooczoocbbangbbang.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.util.Event
import org.sopt.zooczoocbbangbbang.util.NonNullLiveData
import org.sopt.zooczoocbbangbbang.util.NonNullMutableLiveData

class OnboardingViewModel : ViewModel() {
    private val _onboardingStream: NonNullMutableLiveData<Onboarding> =
        NonNullMutableLiveData(Onboarding.INPUT_ROLE)
    val onboardingStream: NonNullLiveData<Onboarding>
        get() = _onboardingStream

    private val _endOnboardingEventStream: MutableLiveData<Event<Unit>> = MutableLiveData()
    val endOnboardingEventStream: LiveData<Event<Unit>> get() = _endOnboardingEventStream

    fun movePreviousStepOnboarding() {
        _onboardingStream.value.previous()?.let { _onboardingStream.value = it }
    }

    fun moveSelectProfileStep() {
        _onboardingStream.value = Onboarding.SELECT_PROFILE
    }

    fun moveStartFamilyCodeStep() {
        _onboardingStream.value = Onboarding.START_FAMILY_CODE
    }

    fun moveRegisterPetStep() {
        _onboardingStream.value = Onboarding.REGISTER_PET
    }

    fun moveInputCodeStep() {
        _onboardingStream.value = Onboarding.INPUT_CODE
    }

    /*fun moveInvitationBeforeStep() {
        _onboardingStream.value = Onboarding.INVITATION_BEFORE
    }

    fun moveInvitationAfterStep() {
        _onboardingStream.value = Onboarding.INVITATION_AFTER
    }*/

    fun moveEndStep() {
        _onboardingStream.value = Onboarding.END
    }

    fun moveHomeStep() {
        _endOnboardingEventStream.value = Event(Unit)
    }
}
