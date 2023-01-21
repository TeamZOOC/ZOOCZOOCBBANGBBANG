package org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.daily

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentDailyBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.RecordViewModel
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.RegisterFragmentState
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody

class DailyFragment : BindingFragment<FragmentDailyBinding>(R.layout.fragment_daily) {
    private val recordViewModel: RecordViewModel by activityViewModels()
    private val dailyViewModel: DailyViewModel by viewModels()
    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            dailyViewModel.imageUrl.value = uri
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = dailyViewModel
        clickAddPhoto()
        observeButtonValidation()
        observeContent()
        changeFragmentState()
    }

    private fun changeFragmentState() {
        recordViewModel.fragmentState.value = RegisterFragmentState.DAILY
    }

    private fun clickAddPhoto() {
        binding.ivYmDailyPosting.setOnClickListener {
            getImage.launch("image/*")
        }
    }

    private fun observeButtonValidation() {
        dailyViewModel.buttonValidation.observe(viewLifecycleOwner) {
            recordViewModel.buttonEnable.value = it
        }
    }

    private fun observeContent() {
        dailyViewModel.imageUrl.observe(viewLifecycleOwner) {
            if (it != null) {
                recordViewModel.imageRequestBody.value = ContentUriRequestBody(requireContext(), it)
                binding.ivYmDailyPosting.load(it)
            }
        }
        dailyViewModel.textContent.observe(viewLifecycleOwner) {
            recordViewModel.textRequestBody.value = it.toRequestBody("text/plain".toMediaType())
        }
    }
}
