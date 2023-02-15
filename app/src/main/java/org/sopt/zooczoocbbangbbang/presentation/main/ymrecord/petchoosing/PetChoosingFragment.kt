package org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.petchoosing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.MultipartBody
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentPetChoosingBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.RecordViewModel
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.RegisterFragmentState

class PetChoosingFragment :
    BindingFragment<FragmentPetChoosingBinding>(R.layout.fragment_pet_choosing) {
    private val recordViewModel: RecordViewModel by activityViewModels()
    private val petChoosingViewModel: PetChoosingViewModel by viewModels()
    private lateinit var petChoosingAdapter: PetChoosingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPetsData()
        observePetsData()
        observePets()
        observeButtonValidation()
        changeFragmentState()
    }

    private fun changeFragmentState() {
        recordViewModel.fragmentState.value = RegisterFragmentState.CHOOSING_PET
    }

    private fun getPetsData() {
        petChoosingViewModel.getPets()
    }

    private fun observePetsData() {
        petChoosingViewModel.pets.observe(viewLifecycleOwner) {
            initAdapter(it.size)
            petChoosingAdapter.setPets(it)
        }
    }

    private fun initAdapter(spanCount: Int) {
        petChoosingAdapter = PetChoosingAdapter { clickItem(it) }
        binding.rvPetChoosing.layoutManager =
            GridLayoutManager(requireContext(), spanCount, RecyclerView.HORIZONTAL, false)
        binding.rvPetChoosing.adapter = petChoosingAdapter
    }

    private fun clickItem(position: Int) {
        petChoosingViewModel.pets.value!![position].isSelected =
            !petChoosingViewModel.pets.value!![position].isSelected
        petChoosingAdapter.setPets(petChoosingViewModel.pets.value!!)
        petChoosingViewModel.petIds.value = petChoosingAdapter.getSelectedPetsId()
    }

    private fun observePets() {
        petChoosingViewModel.petIds.observe(viewLifecycleOwner) {
            recordViewModel.petParts = it.map { petId ->
                MultipartBody.Part.createFormData("pet", petId.toString())
            }
        }
    }

    private fun observeButtonValidation() {
        petChoosingViewModel.buttonValidation.observe(viewLifecycleOwner) {
            recordViewModel.buttonEnable.value = it
        }
    }
}
