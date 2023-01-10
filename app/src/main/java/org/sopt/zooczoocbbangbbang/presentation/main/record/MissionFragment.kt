package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.util.Log
import android.view.View
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class MissionFragment : BindingFragment<FragmentMissionBinding>(R.layout.fragment_mission) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickNextBtn()
    }

    private fun clickNextBtn() {
        Log.d("MissionFragment", "미션 fragment로 돌아가는 로직 필요! 현재 브랜치에는 없음...")
    }

    companion object {
    }
}
