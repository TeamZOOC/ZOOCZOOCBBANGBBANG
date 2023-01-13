package org.sopt.zooczoocbbangbbang.presentation.onboarding.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentOnboardingRoleInputBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.OnboardingViewModel

class RoleInputFragment :
    BindingFragment<FragmentOnboardingRoleInputBinding>(R.layout.fragment_onboarding_role_input) {

    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val roleInputViewModel: RoleInputViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingViewModel = onboardingViewModel
        binding.roleInputViewModel = roleInputViewModel
        observeRoleText()
    }

    private fun observeRoleText() {
        roleInputViewModel.roleTextStream.observe(viewLifecycleOwner) {
            if (it.isNullOrBlank()) {
                roleInputViewModel.disableRegisterRoleButton()
            } else {
                roleInputViewModel.enableRegisterRoleButton()
            }
        }
    }
}
