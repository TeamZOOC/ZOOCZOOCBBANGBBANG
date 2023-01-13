package org.sopt.zooczoocbbangbbang.presentation.onboarding

class OnboardingRepository private constructor() {
    companion object {
        @Volatile
        private var instance: OnboardingRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: OnboardingRepository().apply {
                instance = this
            }
        }
    }
}
