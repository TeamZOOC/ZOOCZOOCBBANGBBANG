package org.sopt.zooczoocbbangbbang.presentation.main.record

import FourSelectorPetFragment
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentRecordBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class RecordFragment : BindingFragment<FragmentRecordBinding>(R.layout.fragment_record) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickImageBtn()
        clickNextBtn()
    }

    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivRecordBgimg.load(it) {
                transformations()
            }
        }
        binding.ivRecordImgbtn.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    private fun clickNextBtn() {
        binding.btnRecordBottom.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_record_view, FourSelectorPetFragment()).commit()
        }
    }

    companion object {
    }
}
