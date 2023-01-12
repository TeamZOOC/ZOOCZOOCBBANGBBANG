package org.sopt.zooczoocbbangbbang.presentation.alarm

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityAlarmBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class AlarmActivity : BindingActivity<ActivityAlarmBinding>(R.layout.activity_alarm) {
    private val alarmViewModel: AlarmViewModel by viewModels()
    private lateinit var alarmAdapter: AlarmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmViewModel.getAlarms()
        initAdapter()
        initData()
    }

    private fun initAdapter() {
        alarmAdapter = AlarmAdapter()
        binding.rvAlarm.adapter = alarmAdapter
    }

    private fun initData() {
        alarmViewModel.alarms.observe(this) {
            alarmAdapter.initAlarms(it)
        }
    }
}
