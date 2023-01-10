package org.sopt.zooczoocbbangbbang.presentation.main.record

import FourSelectorPetFragment
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import timber.log.Timber

class MissionFragment : BindingFragment<FragmentMissionBinding>(R.layout.fragment_mission) {
    private val missionViewModel: MissionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = missionViewModel
        showViewPager()
        clickNextBtn()
    }

    private fun clickNextBtn() {
        binding.btnMissionBottom.setOnClickListener() {
            Timber.tag("MissionFragment").d("반려동물 선택 뷰로 이동하는 로직 필요!")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_record_view, FourSelectorPetFragment()).commit()
        }
    }

    private fun showViewPager() {
        val pagerAdapter = MissionFragmentStateAdapter(requireActivity())
        showNextPager(pagerAdapter)
        pagerAdapter.addFragment(MissionViewPagerFragment())
        pagerAdapter.addFragment(MissionViewPagerFragment())
        pagerAdapter.addFragment(MissionViewPagerFragment())
        pagerAdapter.addFragment(MissionViewPagerFragment())
        pagerAdapter.addFragment(MissionViewPagerFragment())
        pagerAdapter.addFragment(MissionViewPagerFragment())

        binding.vpMissionView.adapter = pagerAdapter
        binding.vpMissionView.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.e("MissionFragment", "Page::: ${position + 1}")
                }
            })

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.vpMissionView.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
        binding.dotsIndicator.attachTo(binding.vpMissionView) // circle indicator 연결
    }

    private fun showNextPager(pagerAdapter: MissionFragmentStateAdapter) {
        val compositePageTransformer = CompositePageTransformer()
        binding.vpMissionView.apply {
            adapter = pagerAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            setPageTransformer(compositePageTransformer)
            setPadding(0, 0, 0, 0)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    companion object {
    }
}
