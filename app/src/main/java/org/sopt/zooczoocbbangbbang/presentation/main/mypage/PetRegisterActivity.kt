package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.os.Bundle
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityRegisterPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class PetRegisterActivity :
    BindingActivity<ActivityRegisterPetBinding>(R.layout.activity_register_pet) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivBtnBack.setOnClickListener {
            finish()
        }
    }
}
