package org.sopt.zooczoocbbangbbang.presentation.onboarding

import android.util.Log

enum class Onboarding(
    val step: OnboardingStep,
    val progress: OnboardingProgress,
    val type: OnboardingType
) {
    INPUT_ROLE(
        OnboardingStep.valueOf(1),
        OnboardingProgress.valueOf(1),
        OnboardingType.DEFAULT
    ),
    SELECT_PROFILE(
        OnboardingStep.valueOf(2),
        OnboardingProgress.valueOf(2),
        OnboardingType.DEFAULT
    ),
    START_FAMILY_CODE(
        OnboardingStep.valueOf(3),
        OnboardingProgress.valueOf(3),
        OnboardingType.DEFAULT
    ),
    REGISTER_PET(
        OnboardingStep.valueOf(4),
        OnboardingProgress.valueOf(4),
        OnboardingType.CREATE_CODE
    ),
    INPUT_CODE(
        OnboardingStep.valueOf(4),
        OnboardingProgress.valueOf(4),
        OnboardingType.INPUT_CODE
    ),

    /*INVITATION_BEFORE(
        OnboardingStep.valueOf(5),
        OnboardingProgress.valueOf(5),
        OnboardingType.CREATE_CODE
    ),
    INVITATION_AFTER(
        OnboardingStep.valueOf(6),
        OnboardingProgress.valueOf(5),
        OnboardingType.CREATE_CODE
    ),*/
    END(
        OnboardingStep.valueOf(5),
        OnboardingProgress.valueOf(5),
        OnboardingType.DEFAULT
    );

    private fun hasStepOf(value: Int) = (this.step == OnboardingStep.valueOf(value))

    fun previous(): Onboarding? {
        Log.d("testthis", this.toString())
        if (this.step.value <= 1) {
            return null
        }
        return values().find {
            Log.d("test", "${this.step.value}")
            if (this.step.value <= 1) {
                it.hasStepOf(this.step.value)
            } else {
                it.hasStepOf(this.step.value - 1)
            }
        }
    }
}
