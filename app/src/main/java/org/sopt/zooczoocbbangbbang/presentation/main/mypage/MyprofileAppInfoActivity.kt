package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.os.Bundle
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityAppInfoBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class MyprofileAppInfoActivity : BindingActivity<ActivityAppInfoBinding>(R.layout.activity_app_info){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.imageView.setOnClickListener{
            finish()
        }
    }
}