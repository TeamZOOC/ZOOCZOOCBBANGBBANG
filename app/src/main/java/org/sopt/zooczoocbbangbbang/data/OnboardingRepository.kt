package org.sopt.zooczoocbbangbbang.data

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
