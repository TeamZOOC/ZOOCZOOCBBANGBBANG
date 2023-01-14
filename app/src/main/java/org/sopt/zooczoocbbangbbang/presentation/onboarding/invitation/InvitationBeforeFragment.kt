package org.sopt.zooczoocbbangbbang.presentation.onboarding.invitation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentOnboardingInvitationShareLinkBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.OnboardingViewModel

class InvitationBeforeFragment :
    BindingFragment<FragmentOnboardingInvitationShareLinkBinding>(R.layout.fragment_onboarding_invitation_share_link) {
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
