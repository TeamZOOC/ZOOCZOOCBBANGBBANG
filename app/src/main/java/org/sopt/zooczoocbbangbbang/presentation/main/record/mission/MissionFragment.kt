package org.sopt.zooczoocbbangbbang.presentation.main.record.mission

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.CustomDialog
import org.sopt.zooczoocbbangbbang.presentation.main.record.MissionFragmentStateAdapter
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.FourSelectorPetFragment
import timber.log.Timber

class MissionFragment : BindingFragment<FragmentMissionBinding>(R.layout.fragment_mission) {
    private val missionViewModel: MissionViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = missionViewModel
        showViewPager()
        clickNextBtn()
        observe()
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

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    if (position == 0 && positionOffsetPixels >= 150) {
                        showMessageDialog()
                        viewLifecycleOwner.lifecycleScope.launch {
                            binding.vpMissionView.setCurrentItem(0, true)
                        }
                    }
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    // 넘어가겠다고 하면 뷰모델 데이터를 초기화
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

    private fun observe() {
        missionViewModel.image.observe(viewLifecycleOwner) {
            Timber.tag("MissionFragment").d("image:::%s", missionViewModel.image.value)
        }
        missionViewModel.missionText.observe(viewLifecycleOwner) {
            Timber.tag("MissionFragment").d("Missiontext:::%s", it)
        }
        missionViewModel.buttonValidation.observe(viewLifecycleOwner) {
            Timber.tag("MissionFragment")
                .d("buttonValidation::: %s", missionViewModel.buttonValidation.value)
            if (missionViewModel.buttonValidation.value == true) {
                binding.btnMissionBottom.setBackgroundResource(R.drawable.shape_green_radius_47)
            }
        } // validation은 true인데 버튼은 활성화되지 않은 건에 대하여...
    }

    private fun showMessageDialog() {
        if (parentFragmentManager.findFragmentByTag("CustomDialog") == null) {
            val customDialog = CustomDialog(finishApp = { requireActivity().finish() })
            customDialog.show(parentFragmentManager, "CustomDialog")
            customDialog.isCancelable = false
        }
    }

    companion object {
    }
}
