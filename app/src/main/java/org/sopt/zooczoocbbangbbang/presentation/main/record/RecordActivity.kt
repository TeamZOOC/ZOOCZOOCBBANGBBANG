package org.sopt.zooczoocbbangbbang.presentation.main.record

import ThreeSelectorPetFragment
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityRecordBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class RecordActivity : BindingActivity<ActivityRecordBinding>(R.layout.activity_record) {
    private lateinit var recordBinding: ActivityRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recordBinding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_record_view, ThreeSelectorPetFragment())
            .commit()
        displayFragment()
        clickCloseBtn()
    }

    private fun displayFragment() {
        binding.tvRecordWrite.setOnClickListener {
            changeFragment(
                RecordFragment()
            )
            binding.tvRecordWrite.setTextColor(Color.parseColor("#4F4F4F"))
            binding.tvRecordMission.setTextColor(Color.parseColor("#BDBDBD"))
        }
        binding.tvRecordMission.setOnClickListener {
            changeFragment(
                MissionFragment()
            )
            binding.tvRecordMission.setTextColor(Color.parseColor("#4F4F4F"))
            binding.tvRecordWrite.setTextColor(Color.parseColor("#BDBDBD"))
        }
    }

    private fun clickCloseBtn() {
        binding.imgbtnRecordBack.setOnClickListener() {
            // finish()
            Log.e("RecordActivity", "close 버튼 누름")
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fcv_record_view, fragment).commit()
    }
}
