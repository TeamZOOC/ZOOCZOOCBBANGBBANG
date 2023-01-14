package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityRecordDoneBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class RecordDoneActivity :
    BindingActivity<ActivityRecordDoneBinding>(R.layout.activity_record_done) {
    private lateinit var recordDoneBinding: ActivityRecordDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recordDoneBinding = ActivityRecordDoneBinding.inflate(layoutInflater)
        clickArchiveBtn()
    }

    private fun clickArchiveBtn() {
        binding.btnRecordDoneBottom.setOnClickListener {
            finish()
        }
    }
}
