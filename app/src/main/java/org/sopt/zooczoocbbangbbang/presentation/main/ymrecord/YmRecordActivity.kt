package org.sopt.zooczoocbbangbbang.presentation.main.ymrecord

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityYmRecordBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordDoneActivity
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.RegisterFragmentState.CHOOSING_PET
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.RegisterFragmentState.DAILY
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.petchoosing.PetChoosingFragment

class YmRecordActivity : BindingActivity<ActivityYmRecordBinding>(R.layout.activity_ym_record) {
    private val recordViewModel: RecordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = recordViewModel
        clickNextButton()
        isPostSuccess()
        clickClose()
    }

    private fun clickClose() {
        binding.ivYmRecordClose.setOnClickListener {
            finish()
        }
    }

    private fun initFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fcv_ym_record, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun clickNextButton() {
        binding.btnYmRecordNext.setOnClickListener {
            when (recordViewModel.fragmentState.value) {
                DAILY -> {
                    initFragment(PetChoosingFragment())
                    recordViewModel.buttonEnable.value = false
                    recordViewModel.fragmentState.value = CHOOSING_PET
                }
                else -> {
                    recordViewModel.postRecord()
                    binding.btnYmRecordNext.isEnabled = false
                }
            }
        }
    }

    private fun isPostSuccess() {
        recordViewModel.isSuccess.observe(this) {
            binding.btnYmRecordNext.isEnabled = true
            if (it) {
                goToRecordDoneActivity()
            }
        }
    }

    private fun goToRecordDoneActivity() {
        val intent = Intent(this, RecordDoneActivity::class.java)
        startActivity(intent)
        finish()
    }
}
