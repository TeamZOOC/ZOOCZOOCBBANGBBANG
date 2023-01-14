package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityRegisterPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class PetRegisterActivity :
    BindingActivity<ActivityRegisterPetBinding>(R.layout.activity_register_pet) {
    private lateinit var petRegisterAdapter: PetRegisterAdapter
    private val petRegisterViewModel: PetRegisterViewModel by viewModels()
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            getImage(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petRegisterViewModel.getPets()
        initAdapter()
        clickBack()
        initPets()
    }

    private fun observeImage() {
        petRegisterViewModel.photo.observe(this) {
        }
    }

    private fun setImageInItem() {
    }

    private fun getImage(uri: Uri?) {
        petRegisterViewModel.photo.value = uri
    }

    private fun clickItem() {
        getContent.launch("*/image")
    }

    private fun clickBack() {
        binding.ivBtnBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        petRegisterAdapter = PetRegisterAdapter(this) { clickItem() }
        binding.rvPets.adapter = petRegisterAdapter
    }

    private fun initPets() {
        petRegisterViewModel.pets.observe(this) {
            petRegisterAdapter.setRegisteredPetlist(it)
        }
    }
}
