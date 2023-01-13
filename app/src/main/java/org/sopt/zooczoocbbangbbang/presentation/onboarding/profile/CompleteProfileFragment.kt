package org.sopt.zooczoocbbangbbang.presentation.onboarding.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentOnboardingProfileCompleteBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.OnboardingViewModel

class CompleteProfileFragment :
    BindingFragment<FragmentOnboardingProfileCompleteBinding>(R.layout.fragment_onboarding_profile_complete) {
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingViewModel = onboardingViewModel
    }
}
