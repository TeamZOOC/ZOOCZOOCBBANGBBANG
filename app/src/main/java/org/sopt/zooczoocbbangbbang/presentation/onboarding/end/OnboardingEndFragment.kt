package org.sopt.zooczoocbbangbbang.presentation.onboarding.end

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentOnboardingEndBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.OnboardingViewModel

class OnboardingEndFragment :
    BindingFragment<FragmentOnboardingEndBinding>(R.layout.fragment_onboarding_end) {
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingViewModel = onboardingViewModel
    }
}
