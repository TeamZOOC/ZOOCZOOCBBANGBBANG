package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionViewPagerBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class MissionViewPagerFragment :
    BindingFragment<FragmentMissionViewPagerBinding>(R.layout.fragment_mission_view_pager) {
    private val missionViewModel: MissionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = missionViewModel
        Log.d("Mission", "MissionViewPagerFragment 20")
        clickImageBtn()
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

    private fun observe() {
        missionViewModel.image.observe(viewLifecycleOwner) {
            Log.d("MissionFragment", "image:::${missionViewModel.image.value}")
        }
        missionViewModel.missionText.observe(viewLifecycleOwner) {
            Log.d("MissionFragment", "Missiontext:::$it")
        }
        missionViewModel.buttonValidation.observe(viewLifecycleOwner) {
            Log.d("MissionFragment", "validation:::$it")
        } // validation은 true인데 버튼은 활성화되지 않은 건에 대하여...
    }

    companion object {
    }
}
