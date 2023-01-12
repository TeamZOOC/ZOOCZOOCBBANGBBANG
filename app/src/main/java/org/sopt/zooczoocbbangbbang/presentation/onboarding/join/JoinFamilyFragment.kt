package org.sopt.zooczoocbbangbbang.presentation.onboarding.join

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentOnboardingInputCodeBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.OnboardingViewModel

class JoinFamilyFragment :
    BindingFragment<FragmentOnboardingInputCodeBinding>(R.layout.fragment_onboarding_input_code) {

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val joinFamilyViewModel: JoinFamilyViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingViewModel = onboardingViewModel
        binding.joinFamilyViewModel = joinFamilyViewModel
        observeCodeInputForm()
    }

    private fun observeCodeInputForm() {
        joinFamilyViewModel.codeInputStream.observe(viewLifecycleOwner) {
            if (it.isNullOrBlank()) {
                joinFamilyViewModel.disableJoinFamilyButton()
            } else {
                joinFamilyViewModel.enableJoinFamilyButton()
            }
        }
    }
}
