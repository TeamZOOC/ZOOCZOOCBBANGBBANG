package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class MissionFragment : BindingFragment<FragmentMissionBinding>(R.layout.fragment_mission) {
    private val missionViewModel: MissionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = missionViewModel
        clickImageBtn()
        clickNextBtn()
        observe()
    }

    // 사진을 불러오는 함수
    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivMissionBgimg.load(it) {
                transformations()
                missionViewModel.image.value = it
            }
        }
        binding.ivMissionImgbtn.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    private fun clickNextBtn() {
        binding.btnMissionBottom.setOnClickListener() {
            Log.d("MissionFragment", "반려동물 선택 뷰로 이동하는 로직 필요!")
        }
    }

    private fun observe() {
        missionViewModel.image.observe(viewLifecycleOwner) {
            Log.d("MissionFragment", "image:::${missionViewModel.image.value}")
        }
        missionViewModel.missionText.observe(viewLifecycleOwner) {
            Log.d("MissionFragment", "Recordtext:::$it")
        }
        missionViewModel.buttonValidation.observe(viewLifecycleOwner) {
            Log.d("MissionFragment", "validation:::$it")
        }
    }

    companion object {
    }
}
