package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.os.Bundle
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivitySetAlarmBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class MyprofileSetAlarmActivity :
    BindingActivity<ActivitySetAlarmBinding>(R.layout.activity_set_alarm) {
    override fun onCreate(savedInstanceState: Bundle?) {
        var checkischeked = false
        super.onCreate(savedInstanceState)
        binding.imageView.setOnClickListener {
            finish()
        }
        binding.imageView2.setOnClickListener {
            if (checkischeked == false) {
                binding.imageView2.setImageResource(R.drawable.ic_tooglebtn_off)
            } else {
                binding.imageView2.setImageResource(R.drawable.ic_togglebtn_on)
            }
            checkischeked = !checkischeked
        }
    }
}
