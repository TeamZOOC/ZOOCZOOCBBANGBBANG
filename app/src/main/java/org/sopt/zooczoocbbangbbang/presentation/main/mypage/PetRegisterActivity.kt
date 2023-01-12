package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityRegisterPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class PetRegisterActivity :
    BindingActivity<ActivityRegisterPetBinding>(R.layout.activity_register_pet) {
    private lateinit var petRegisterAdapter: PetRegisterAdapter
    private val memberviewModel: MemberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        getRegisteredPet()
        binding.ivBtnBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        petRegisterAdapter = PetRegisterAdapter(this)
        binding.rvPets.adapter = petRegisterAdapter
    }

    // private fun registerPet() {
    //     registerPetViewModel.registerData.observe(this) { pet ->
    //         if (pet != null) {
    //             petRegisterAdapter.setPetlist(pet)
    //         }
    //     }
    // }

    private fun getRegisteredPet() {
        memberviewModel.petData.observe(this) { pet ->
            if (pet != null) {
                petRegisterAdapter.setRegisteredPetlist(pet)
            }
        }
    }
}
