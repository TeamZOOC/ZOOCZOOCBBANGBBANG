package org.sopt.zooczoocbbangbbang.presentation.main.record.daily

import ThreeSelectorPetFragment
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentRecordBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordDoneActivity
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.FourSelectorPetFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.TwoSelectorPetFragment
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import timber.log.Timber

class RecordFragment : BindingFragment<FragmentRecordBinding>(R.layout.fragment_record) {
    private val recordViewModel: RecordViewModel by activityViewModels()

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
            recordViewModel.getPetNum()
            recordViewModel.petNum.observe(viewLifecycleOwner) {
                Timber.tag("Mission").d("petNum::: %s", recordViewModel.petNum.value)
                when (it) {
                    1 -> {
                        val intent = Intent(requireContext(), RecordDoneActivity::class.java)
                        startActivity(intent)
                    }
                    2 -> parentFragmentManager.beginTransaction()
                        .replace(R.id.fcv_record_view, TwoSelectorPetFragment()).commit()
                    3 -> parentFragmentManager.beginTransaction()
                        .replace(R.id.fcv_record_view, ThreeSelectorPetFragment()).commit()
                    4 -> parentFragmentManager.beginTransaction()
                        .replace(R.id.fcv_record_view, FourSelectorPetFragment()).commit()
                }
            }
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
}
