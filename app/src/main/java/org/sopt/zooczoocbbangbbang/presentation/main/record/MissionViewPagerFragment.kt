package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionViewPagerBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class MissionViewPagerFragment :
    BindingFragment<FragmentMissionViewPagerBinding>(R.layout.fragment_mission_view_pager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickImageBtn()
    }

    // 사진을 불러오는 함수
    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivMissionBgimg.load(it) {
                transformations()
                // missionViewModel.image.value = it
            }
        }
        binding.ivMissionImgbtn.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    companion object {
    }
}
