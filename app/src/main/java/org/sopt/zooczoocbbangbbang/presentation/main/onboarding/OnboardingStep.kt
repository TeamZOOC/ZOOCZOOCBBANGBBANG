package org.sopt.zooczoocbbangbbang.presentation.main.onboarding

class OnboardingStep private constructor(
    val value: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OnboardingStep

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }

    override fun toString(): String {
        return "$value"
    }

    companion object {
        const val START_STEP = 1
        const val END_STEP = 7

        fun valueOf(value: Int): OnboardingStep {
            validateOnboardingStepRange(value)
            return OnboardingStepCache.cache[value - 1]
        }

        private fun validateOnboardingStepRange(value: Int) {
            require(value in START_STEP..END_STEP) {
                "온보딩은 $START_STEP 에서 $END_STEP 까지 있습니다. 현재 스텝 정보: $value"
            }
        }

        private class OnboardingStepCache {
            companion object {
                val cache: List<OnboardingStep> = (START_STEP..END_STEP).map { OnboardingStep(it) }
            }
        }
    }
}
