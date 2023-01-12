package org.sopt.zooczoocbbangbbang.presentation.main.record.daily

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentRecordBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.FourSelectorPetFragment
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import timber.log.Timber

class RecordFragment : BindingFragment<FragmentRecordBinding>(R.layout.fragment_record) {
    private val recordViewModel: RecordViewModel by activityViewModels()

    // private val sharedRecordViewModel: SharedRecordViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = recordViewModel
        clickImageBtn()
        clickNextBtn()
        observe()
    }

    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivRecordBgimg.load(it) {
                transformations()
                recordViewModel.image.value =
                    it?.let { uri -> ContentUriRequestBody(requireContext(), uri) }
            }
        }
        binding.ivRecordImgbtn.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    // 반려동물 선택 뷰로 넘어가는 로직 필요
    private fun clickNextBtn() {
        binding.btnRecordBottom.setOnClickListener {
            // recordViewModel.isRecordPostSuccess.observe(viewLifecycleOwner) {
            //     parentFragmentManager.beginTransaction()
            //         .replace(R.id.fcv_record_view, FourSelectorPetFragment()).commit()
            // }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_record_view, FourSelectorPetFragment()).commit()
        }
    }

    private fun observe() {
        recordViewModel.image.observe(viewLifecycleOwner) {
            Timber.tag("observe").d("image:::%s", recordViewModel.image.value)
        }
        recordViewModel.recordText.observe(viewLifecycleOwner) {
            Timber.tag("observe").d("Recordtext:::%s", it)
        }
        recordViewModel.buttonValidation.observe(viewLifecycleOwner) {
            Timber.tag("observe").d("validation:::%s", it)
        }
    }

    companion object {
    }
}
