package org.sopt.zooczoocbbangbbang.presentation.main.record.daily

import ThreeSelectorPetFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentDailyBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.FragmentState
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordDoneActivity
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordViewModel
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.FourSelectorPetFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.TwoSelectorPetFragment
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import timber.log.Timber

class DailyFragment : BindingFragment<FragmentDailyBinding>(R.layout.fragment_daily) {
    private var prevFragmentState: FragmentState = FragmentState.DAILY
    private val dailyViewModel: DailyViewModel by activityViewModels()
    private val sharedViewModel: RecordViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = dailyViewModel
        clickImageBtn()
        clickNextBtn(binding.btnRecordBottom)
        observe()
    }

    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivRecordBgimg.load(it) {
                transformations()
                dailyViewModel.image.value =
                    it?.let { uri -> ContentUriRequestBody(requireContext(), uri) }
                Log.d("Record", "image ::: ${dailyViewModel.image.value}")
            }
        }
        binding.ivRecordImgbtn.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    private fun uploadImageAndText(image: ContentUriRequestBody?, text: String) {
        val dailyImage = dailyViewModel.image.value
        val dailyContent = dailyViewModel.recordText.value
        sharedViewModel.image = dailyImage
        sharedViewModel.text = dailyContent
        Log.d("DailyFragment", "Image: $dailyImage, Text: $dailyContent")
    }

    private fun clickNextBtn(view: View) {
        binding.btnRecordBottom.setOnClickListener {
            Log.d("Record", "62")
            val dailyImage = dailyViewModel.image.value
            val dailyContent = dailyViewModel.recordText.toString()
            Log.d("Record", "65")
            uploadImageAndText(dailyImage, dailyContent)
            dailyViewModel.getPetNum()
            dailyViewModel.petNum.observe(viewLifecycleOwner) {
                Timber.tag("Record").d("petNum::: %s", dailyViewModel.petNum.value)
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
        dailyViewModel.image.observe(viewLifecycleOwner) {
            Timber.tag("Record").d("image:::%s", dailyViewModel.image.value)
        }
        dailyViewModel.recordText.observe(viewLifecycleOwner) {
            Timber.tag("Record").d("Recordtext:::%s", it)
        }
        dailyViewModel.buttonValidation.observe(viewLifecycleOwner) {
            Timber.tag("Record").d("validation:::%s", it)
        }
    }
}
