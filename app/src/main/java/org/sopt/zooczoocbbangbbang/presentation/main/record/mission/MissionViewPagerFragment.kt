package org.sopt.zooczoocbbangbbang.presentation.main.record.mission

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMissionViewPagerBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import timber.log.Timber

class MissionViewPagerFragment :
    BindingFragment<FragmentMissionViewPagerBinding>(R.layout.fragment_mission_view_pager) {
    private val missionViewModel: MissionViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = missionViewModel
        Timber.tag("Mission").d("MissionViewPagerFragment 20")
        clickImageBtn()
    }

    private fun clickImageBtn() {
        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.ivMissionBgimg.load(it) {
                transformations()
                missionViewModel.image.value =
                    it?.let { uri -> ContentUriRequestBody(requireContext(), uri) }
            }
        }
        binding.ivMissionImgbtn.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    companion object {
    }
}
