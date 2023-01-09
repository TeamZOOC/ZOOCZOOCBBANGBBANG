package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class MissionFragment : BindingFragment<FragmentMissionBinding>(R.layout.fragment_mission) {
    private val missionViewModel: MissionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = missionViewModel
        val pagerAdapter = MissionFragmentStateAdapter(requireActivity())

        // fragment add
        pagerAdapter.addFragment(MissionViewPagerFragment())
        pagerAdapter.addFragment(MissionViewPagerFragment())
        pagerAdapter.addFragment(MissionViewPagerFragment())

        binding.fcvMissionView.adapter = pagerAdapter
        binding.fcvMissionView.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.e("MissionFragment", "Page::: ${position + 1}")
                }
            })
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
