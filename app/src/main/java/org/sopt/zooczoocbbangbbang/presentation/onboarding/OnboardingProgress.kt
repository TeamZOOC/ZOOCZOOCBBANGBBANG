package org.sopt.zooczoocbbangbbang.presentation.onboarding

class OnboardingProgress private constructor(
    val value: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OnboardingProgress

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
        const val START_PROGRESS = 1
        const val END_PROGRESS = 5

        fun valueOf(value: Int): OnboardingProgress {
            validateOnboardingStepRange(value)
            return OnboardingProgressCache.cache[value - 1]
        }

        private fun validateOnboardingStepRange(value: Int) {
            require(value in START_PROGRESS..END_PROGRESS) {
                "온보딩 진행도는 $START_PROGRESS 에서 $END_PROGRESS 까지 있습니다. 현재 진행도: $value"
            }
        }

        private class OnboardingProgressCache {
            companion object {
                val cache: List<OnboardingProgress> =
                    (START_PROGRESS..END_PROGRESS).map { OnboardingProgress(it) }
            }
        }
    }
}
