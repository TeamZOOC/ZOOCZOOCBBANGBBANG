package org.sopt.zooczoocbbangbbang.presentation.main.record.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentTwoSelectorPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordDoneActivity
import org.sopt.zooczoocbbangbbang.presentation.main.record.daily.RecordViewModel
import org.sopt.zooczoocbbangbbang.presentation.main.record.mission.MissionViewModel
import timber.log.Timber

open class TwoSelectorPetFragment :
    BindingFragment<FragmentTwoSelectorPetBinding>(R.layout.fragment_two_selector_pet) {
    private val twoSelectorViewModel: TwoSelectorPetViewModel by viewModels()
    private val missionViewModel: MissionViewModel by activityViewModels()
    private val recordViewModel: RecordViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = twoSelectorViewModel
        super.onViewCreated(view, savedInstanceState)
        twoSelectorViewModel.getPetInfo()
        fetchPetImage()
        checkIsSelected()
        clickRecordBtn()
        observeSelectComplete()
        observePostCoompleted()
    }

    private fun clickFirstSelectorItem() {
        binding.clTwoSelectorPetFirstLayout.setOnClickListener {
            twoSelectorViewModel.switchFirstBooleanValue()
            if (twoSelectorViewModel.isSelectedFirst.value == true) {
                binding.tvTwoSelectorPetFirst.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvTwoSelectorPetFirst.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickSecondSelectorItem() {
        binding.clTwoSelectorPetSecondLayout.setOnClickListener {
            twoSelectorViewModel.switchSecondBooleanValue()
            if (twoSelectorViewModel.isSelectedSecond.value == true) {
                binding.tvTwoSelectorPetSecond.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvTwoSelectorPetSecond.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun checkIsSelected() {
        twoSelectorViewModel.isSelectedFirst.observe(viewLifecycleOwner) {
            Timber.tag("TwoSelector")
                .d("isSelected::: %s", twoSelectorViewModel.isSelectedFirst.value)
            clickFirstSelectorItem()
        }
        twoSelectorViewModel.isSelectedSecond.observe(viewLifecycleOwner) {
            Timber.tag("TwoSelector")
                .d("isSelected::: %s", twoSelectorViewModel.isSelectedSecond.value)
            clickSecondSelectorItem()
        }
    }

    private fun clickBackBtn() {
        binding.ivTwoSelectorPetBackbtn.setOnClickListener {
            // parentFragmentManager.beginTransaction()
            //     .replace(R.id.fcv_record_view, RecordFragment()).addToBackStack(null).commit()
        }
    }

    private fun fetchPetImage() {
        twoSelectorViewModel.petImageList.observe(viewLifecycleOwner) {
            binding.ivTwoSelectorPetFirst.load(twoSelectorViewModel.petImageList.value?.get(0)) {
                transformations(CircleCropTransformation())
            }
            binding.ivTwoSelectorPetSecond.load(twoSelectorViewModel.petImageList.value?.get(1)) {
                transformations(CircleCropTransformation())
            }
        }
    }

    private fun clickRecordBtn() {
        binding.btnTwoSelectorPetBottom.setOnClickListener {
            Log.d("qwer", "0차 진입점")
            gatherPets()
        }
    }

    private fun gatherPets() {
        val tempPets = mutableListOf<Int>()

        if (twoSelectorViewModel.isSelectedFirst.value == true) {
            tempPets.add(twoSelectorViewModel.petIdList[0])
        }
        if (twoSelectorViewModel.isSelectedSecond.value == true) {
            tempPets.add(twoSelectorViewModel.petIdList[1])
        }

        Log.d("qwer", "1차 진입점")
        missionViewModel.selectedPets.value = tempPets.toList()
        recordViewModel.selectedPets.value = tempPets.toList()
    }

    private fun observeSelectComplete() {
        missionViewModel.selectedPets.observe(viewLifecycleOwner) {
            Log.d("qwer", "list1: $it")
            if (!it.isNullOrEmpty()) {
                Log.d("qwer", "list2: $it")
                missionViewModel.onSubmit()
            }
        }
        recordViewModel.selectedPets.observe(viewLifecycleOwner) {
            Log.d("qwer", "list1: $it")
            if (!it.isNullOrEmpty()) {
                Log.d("qwer", "list2: $it")
                recordViewModel.onSubmit()
            }
        }
    }

    private fun observePostCoompleted() {
        missionViewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Log.d("qwer", "여기 온거 맞지?")
                val intent = Intent(requireContext(), RecordDoneActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
        recordViewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Log.d("qwer", "여기 온거 맞지?")
                val intent = Intent(requireContext(), RecordDoneActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }
}
