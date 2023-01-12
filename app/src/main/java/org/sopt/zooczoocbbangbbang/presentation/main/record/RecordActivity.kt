package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityRecordBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.record.daily.RecordFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.mission.MissionFragment
import timber.log.Timber

class RecordActivity : BindingActivity<ActivityRecordBinding>(R.layout.activity_record) {
    private lateinit var recordBinding: ActivityRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recordBinding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_record_view, RecordFragment())
            .commit()
        displayFragment()
        clickCloseBtn()
    }

    private fun displayFragment() {
        binding.tvRecordWrite.setOnClickListener {
            displayFragment(
                RecordFragment()
            )
            binding.tvRecordWrite.setTextColor(Color.parseColor("#4F4F4F"))
            binding.tvRecordMission.setTextColor(Color.parseColor("#BDBDBD"))
        }
        binding.tvRecordMission.setOnClickListener {
            displayFragment(
                MissionFragment()
            )
            binding.tvRecordMission.setTextColor(Color.parseColor("#4F4F4F"))
            binding.tvRecordWrite.setTextColor(Color.parseColor("#BDBDBD"))
        }
    }

    private fun clickCloseBtn() {
        binding.imgbtnRecordClose.setOnClickListener {
            Timber.tag("RecordActivity").e("커스텀 다이얼로그 뜨는 로직 필요!")
            showMessageDialog()
        }
    }

    private fun showMessageDialog() {
        val customDialog = CustomDialog { finish() }
        customDialog.show(supportFragmentManager, "CustomDialog")
        customDialog.isCancelable = false
    }

    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fcv_record_view, fragment).commit()
    }
}
