package org.sopt.zooczoocbbangbbang.presentation.main.record.mission

import ThreeSelectorPetFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.MissionFragmentStateAdapter
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordDoneActivity
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.FourSelectorPetFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.TwoSelectorPetFragment
import org.sopt.zooczoocbbangbbang.util.CustomAlertDialog
import timber.log.Timber

class MissionFragment : BindingFragment<FragmentMissionBinding>(R.layout.fragment_mission) {
    private val missionViewModel: MissionViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = missionViewModel
        missionViewModel.getMission()
        showViewPager()
        clickNextBtn()
        observe()
    }

    private fun clickNextBtn() {
        binding.btnMissionBottom.setOnClickListener {
            missionViewModel.getPetNum()
            missionViewModel.petNum.observe(viewLifecycleOwner) {
                Log.d("Mission", "petNum::: ${missionViewModel.petNum.value}")
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

    private fun showViewPager() {
        val pagerAdapter = MissionFragmentStateAdapter(requireActivity())
        showNextPager(pagerAdapter)
        missionViewModel.missionList.observe(viewLifecycleOwner) {
            Timber.tag("MissionFragment")
                .d("missonList !!! " + missionViewModel.missionList.value?.size)
            Timber.tag("MissionFragment").d("hhuhj")
            val fragments = mutableListOf<Fragment>()
            repeat(it.size) {
                fragments.add(MissionViewPagerFragment())
            }
            pagerAdapter.addFragment(fragments)

            Timber.tag("MissionFragment").d("fragments::: %s", pagerAdapter.fragments.size)
        }

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
                        // showMessageDialog()
                    }
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    // ?????? ????????? ?????? ???????????? ??????
                    missionViewModel.position.value = position
                    missionViewModel.isPageScrolling = true
                    // viewpager fragment?????? ?????? ????????? observing?????? index??? ????????????
                    // ?????????????????? ?????? ????????? ???????????? ?????????
                    Log.e("qwer", "Page::: ${position + 1}")
                }
            })

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.vpMissionView.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
        binding.dotsIndicator.attachTo(binding.vpMissionView) // circle indicator ??????
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
        }
    }

    private fun showMessageDialog() {
        // viewLifecycleOwner.lifecycleScope.launch {
        //     binding.vpMissionView.setCurrentItem(0, true)
        //     missionViewModel.isPageScrolling = false
        // }
        if (parentFragmentManager.findFragmentByTag("CustomDialog") == null) {
            // val customDialog = CustomDialog(finishApp = { requireActivity().finish() })
            // customDialog.show(parentFragmentManager, "CustomDialog")
            // customDialog.isCancelable = false
            if (!missionViewModel.isDialogShown.value!! && missionViewModel.isPageScrolling) {
                val dialog = CustomAlertDialog(
                    requireContext(),
                    "???????????? ???????????????????",
                    "?????? ????????? ????????? ???????????? ?????????",
                    "????????????",
                    "?????????",
                    {
                        missionViewModel.isDialogShown.value = false
                        missionViewModel.isPageScrolling = false
                    },
                    {
                        binding.vpMissionView.currentItem = binding.vpMissionView.currentItem + 1
                        missionViewModel.isDialogShown.value = false
                        missionViewModel.isPageScrolling = false
                    }
                )
                Log.d("Mission", "???????????????")
                dialog.showDialog { missionViewModel.isDialogShown.value = true }
            }
        }
    }
}
