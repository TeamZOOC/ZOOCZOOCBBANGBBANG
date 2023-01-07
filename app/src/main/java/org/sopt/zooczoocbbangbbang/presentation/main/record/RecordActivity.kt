package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
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
            .replace(R.id.fcv_record_view, MissionFragment())
            .commit()
    }
}
