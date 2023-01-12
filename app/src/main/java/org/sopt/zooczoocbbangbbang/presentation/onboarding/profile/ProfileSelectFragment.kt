package org.sopt.zooczoocbbangbbang.presentation.onboarding.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentOnboardingProfileSelectorBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.OnboardingViewModel

class ProfileSelectFragment :
    BindingFragment<FragmentOnboardingProfileSelectorBinding>(R.layout.fragment_onboarding_profile_selector) {
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val profileSelectViewModel: ProfileSelectViewModel by viewModels()
    private val pickProfileImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                profileSelectViewModel.onSaveProfileImage(uri.toString())
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingViewModel = onboardingViewModel
        binding.profileSelectViewModel = profileSelectViewModel
        observeProfileSelect()
    }

    private fun observeProfileSelect() {
        profileSelectViewModel.popUpProfilePickerEventStream.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                pickProfileImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }
}
