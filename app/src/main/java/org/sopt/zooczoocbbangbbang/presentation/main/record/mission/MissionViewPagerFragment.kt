package org.sopt.zooczoocbbangbbang.presentation.main.record.mission

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionViewPagerBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import timber.log.Timber

class MissionViewPagerFragment :
    BindingFragment<FragmentMissionViewPagerBinding>(R.layout.fragment_mission_view_pager) {
    private val missionViewModel: MissionViewModel by activityViewModels()
    private val missionViewPagerViewModel: MissionViewPagerViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = missionViewPagerViewModel
        Timber.tag("Mission").d("MissionViewPagerFragment 20")
        clickImageBtn()
        observeEditText()
        observeImage()
        missionViewModel.position.observe(viewLifecycleOwner) {
            Log.d("Mission", "missionList:::: ${missionViewModel.missionList.value}")
            binding.tvMissionQuestion.text = missionViewModel.missionList.value?.get(it)?.missionContent
        }
    }

    private fun observeEditText() {
        missionViewPagerViewModel.content.observe(viewLifecycleOwner) {
            Log.d("qwer", "content: $it")
            missionViewModel.checkMissionText(it)
        }
    }

    private fun observeImage() {
        missionViewPagerViewModel.image.observe(viewLifecycleOwner) {
            Log.d("qwer", "image not null: ${it != null}")
            missionViewModel.checkImage(it)
        }
    }

    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivMissionBgimg.load(it) {
                transformations()
                missionViewPagerViewModel.image.value =
                    it?.let { uri -> ContentUriRequestBody(requireContext(), uri) }
            }
        }
        binding.ivMissionImgbtn.setOnClickListener {
            launcher.launch("image/*")
        }
    }
}
