package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class MissionFragment : BindingFragment<FragmentMissionBinding>(R.layout.fragment_mission) {
    private val missionViewModel: MissionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = missionViewModel

        clickNextBtn()
    }

    private fun clickNextBtn() {
        binding.btnMissionBottom.setOnClickListener() {
            Log.d("MissionFragment", "반려동물 선택 뷰로 이동하는 로직 필요!")
        }
    }

    companion object {
    }
}
