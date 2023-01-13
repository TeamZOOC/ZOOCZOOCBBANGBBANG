package org.sopt.zooczoocbbangbbang.presentation.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityOnboardingBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.onboarding.create.PetRegisterFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.end.OnboardingEndFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.invitation.InvitationAfterFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.invitation.InvitationBeforeFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.join.JoinFamilyFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.profile.CompleteProfileFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.profile.ProfileSelectFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.profile.RoleInputFragment
import org.sopt.zooczoocbbangbbang.util.replaceFragmentInActivity

class OnboardingActivity :
    BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onboardingViewModel = onboardingViewModel
        observeFragmentChangeStream()
    }

    private fun observeFragmentChangeStream() {
        onboardingViewModel.onboardingStream.observe(this) {
            when (it) {
                Onboarding.INPUT_ROLE -> replaceRoleInputFragment()
                Onboarding.SELECT_PROFILE -> replaceSelectProfileFragment()
                Onboarding.START_FAMILY_CODE -> replaceCompleteProfileFragment()
                Onboarding.REGISTER_PET -> replacePetRegisterFragment()
                Onboarding.INVITATION_BEFORE -> replaceInvitationBeforeFragment()
                Onboarding.INVITATION_AFTER -> replaceInvitationAfterFragment()
                Onboarding.INPUT_CODE -> replaceJoinFamilyFragment()
                Onboarding.END -> replaceOnboardingEndFragment()
            }
        }
    }

    private fun replaceRoleInputFragment() {
        replaceFragmentInActivity(
            RoleInputFragment(),
            R.id.fcv_onboarding
        )
    }

    private fun replaceSelectProfileFragment() {
        replaceFragmentInActivity(
            ProfileSelectFragment(),
            R.id.fcv_onboarding
        )
    }

    private fun replaceCompleteProfileFragment() {
        replaceFragmentInActivity(
            CompleteProfileFragment(),
            R.id.fcv_onboarding
        )
    }

    private fun replacePetRegisterFragment() {
        replaceFragmentInActivity(
            PetRegisterFragment(),
            R.id.fcv_onboarding
        )
    }

    private fun replaceInvitationBeforeFragment() {
        replaceFragmentInActivity(
            InvitationBeforeFragment(),
            R.id.fcv_onboarding
        )
    }

    private fun replaceInvitationAfterFragment() {
        replaceFragmentInActivity(
            InvitationAfterFragment(),
            R.id.fcv_onboarding
        )
    }

    private fun replaceJoinFamilyFragment() {
        replaceFragmentInActivity(
            JoinFamilyFragment(),
            R.id.fcv_onboarding
        )
    }

    private fun replaceOnboardingEndFragment() {
        replaceFragmentInActivity(
            OnboardingEndFragment(),
            R.id.fcv_onboarding
        )
    }
}
