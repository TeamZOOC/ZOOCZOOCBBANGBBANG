package org.sopt.zooczoocbbangbbang.presentation.onboarding.create

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentOnboardingPetRegisterBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.onboarding.OnboardingViewModel

class PetRegisterFragment :
    BindingFragment<FragmentOnboardingPetRegisterBinding>(R.layout.fragment_onboarding_pet_register) {
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val petRegisterViewModel: PetRegisterViewModel by viewModels()
    private lateinit var petRegisterFormAdapter: PetRegisterFormAdapter
    private var selectedPosition: Int = 0

    private val pickProfileImage = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            petRegisterFormAdapter.editItemImage(selectedPosition, uri.toString())
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingViewModel = onboardingViewModel
        binding.petRegisterViewModel = petRegisterViewModel
        petRegisterFormAdapter = PetRegisterFormAdapter(
            onCancelListener = { petRegisterViewModel.removePetRegisterForm(it) },
            onSelectImageListener = {
                selectedPosition = it
                pickProfileImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        )
        binding.rcvOnboardingRegisterPets.adapter = petRegisterFormAdapter
        observeAddPetEvent()
        observeDelPetEvent()
    }

    private fun observeAddPetEvent() {
        petRegisterViewModel.addPetFormEventStream.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                petRegisterFormAdapter.addNewForm()
                toggleAddPetButton()
            }
        }
    }

    private fun toggleAddPetButton() {
        if (petRegisterFormAdapter.isMaxContent()) {
            petRegisterViewModel.toggleDisableAddPetButton()
        } else {
            petRegisterViewModel.toggleEnableAddPetButton()
        }
    }

    private fun observeDelPetEvent() {
        petRegisterViewModel.delPetFormEventStream.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { position ->
                petRegisterFormAdapter.deleteForm(position)
            }
        }
    }
}
