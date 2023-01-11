package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentRecordBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.FourSelectorPetFragment
import timber.log.Timber

class RecordFragment : BindingFragment<FragmentRecordBinding>(R.layout.fragment_record) {
    private val recordViewModel: RecordViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = recordViewModel
        clickImageBtn()
        clickNextBtn()
        observe()
    }

    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivRecordBgimg.load(it) {
                transformations()
                recordViewModel.image.value = it
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

    private fun observe() {
        recordViewModel.image.observe(viewLifecycleOwner) {
            Timber.tag("RecordFragment").d("image:::%s", recordViewModel.image.value)
        }
        recordViewModel.recordText.observe(viewLifecycleOwner) {
            Timber.tag("RecordFragment").d("Recordtext:::%s", it)
        }
        recordViewModel.buttonValidation.observe(viewLifecycleOwner) {
            Timber.tag("RecordFragment").d("validation:::%s", it)
        }
    }

    companion object {
    }
}
