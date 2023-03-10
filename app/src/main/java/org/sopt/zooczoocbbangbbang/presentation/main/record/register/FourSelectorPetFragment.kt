package org.sopt.zooczoocbbangbbang.presentation.main.record.register

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentFourSelectorPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.daily.RecordViewModel
import org.sopt.zooczoocbbangbbang.presentation.main.record.mission.MissionViewModel
import timber.log.Timber

class FourSelectorPetFragment :
    BindingFragment<FragmentFourSelectorPetBinding>(R.layout.fragment_four_selector_pet) {
    private val fourSelectorViewModel: FourSelectorPetViewModel by viewModels()
    private val missionViewModel: MissionViewModel by activityViewModels()
    private val recordViewModel: RecordViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = fourSelectorViewModel
        super.onViewCreated(view, savedInstanceState)
        fourSelectorViewModel.getPetInfo()
        fetchPetImage()
        checkIsSelected()
        clickRecordBtn()
        // clickBackBtn()
    }

    private fun clickFirstSelectorItem() {
        binding.clFourSelectorPet1.setOnClickListener {
            fourSelectorViewModel.switchFirstBooleanValue()
            if (fourSelectorViewModel.isSelectedFirst.value == true) {
                binding.tvFourSelectorPetFirst.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvFourSelectorPetFirst.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickSecondSelectorItem() {
        binding.clFourSelectorPet2.setOnClickListener {
            fourSelectorViewModel.switchSecondBooleanValue()
            if (fourSelectorViewModel.isSelectedSecond.value == true) {
                binding.tvFourSelectorPetSecond.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvFourSelectorPetSecond.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickThreeSelectorItem() {
        binding.clFourSelectorPet3.setOnClickListener {
            fourSelectorViewModel.switchThirdBooleanValue()
            if (fourSelectorViewModel.isSelectedThird.value == true) {
                binding.tvFourSelectorPetThird.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvFourSelectorPetThird.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickAllSelectorItem() {
        binding.clFourSelectorPet4.setOnClickListener {
            fourSelectorViewModel.switchFourthBooleanValue()
            if (fourSelectorViewModel.isSelectedFourth.value == true) {
                binding.tvFourSelectorPetFourth.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvFourSelectorPetFourth.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun checkIsSelected() {
        fourSelectorViewModel.isSelectedFirst.observe(viewLifecycleOwner) {
            Timber.tag("ThreeSelector")
                .d("isSelected::: %s", fourSelectorViewModel.isSelectedFirst.value)
            clickFirstSelectorItem()
        }
        fourSelectorViewModel.isSelectedSecond.observe(viewLifecycleOwner) {
            Timber.tag("ThreeSelector")
                .d("isSelected::: %s", fourSelectorViewModel.isSelectedSecond.value)
            clickSecondSelectorItem()
        }
        fourSelectorViewModel.isSelectedThird.observe(viewLifecycleOwner) {
            Timber.tag("ThreeSelector")
                .d("isSelected::: %s", fourSelectorViewModel.isSelectedThird.value)
            clickThreeSelectorItem()
        }
        fourSelectorViewModel.isSelectedFourth.observe(viewLifecycleOwner) {
            Timber.tag("ThreeSelector")
                .d("isSelected::: %s", fourSelectorViewModel.isSelectedFourth.value)
            clickAllSelectorItem()
        }
    }

    private fun clickRecordBtn() {
        binding.btnFourSelectorPetBottom.setOnClickListener {
            Log.d("qwer", "0??? ?????????")
            gatherPets()
            // requireActivity().finish()
        }
    }

    private fun gatherPets() {
        val selectedPets = mutableListOf<Int>()

        if (fourSelectorViewModel.isSelectedFirst.value == true) {
            selectedPets.add(fourSelectorViewModel.petIdList[0])
        }
        if (fourSelectorViewModel.isSelectedSecond.value == true) {
            selectedPets.add(fourSelectorViewModel.petIdList[1])
        }
        if (fourSelectorViewModel.isSelectedThird.value == true) {
            selectedPets.add(fourSelectorViewModel.petIdList[2])
        }
        if (fourSelectorViewModel.isSelectedFourth.value == true) {
            selectedPets.add(fourSelectorViewModel.petIdList[3])
        }

        Log.d("qwer", "1??? ?????????")
        missionViewModel.selectedPets.value = selectedPets
        recordViewModel.selectedPets.value = selectedPets
    }

    // ?????? ?????? ??????????????? ??????????????? ????????? ???????
    // private fun clickBackBtn() {
    //     binding.ivFourSelectorPetBackbtn.setOnClickListener {
    //         parentFragmentManager.beginTransaction()
    //             .replace(R.id.fcv_record_view, RecordFragment()).addToBackStack(null).commit()
    //     }
    // }

    private fun fetchPetImage() {
        binding.ivFourSelectorPetFirst.load(fourSelectorViewModel.petImageList.value?.get(0)) {
            transformations(
                CircleCropTransformation()
            )
        }
        binding.ivFourSelectorPetSecond.load(fourSelectorViewModel.petImageList.value?.get(1)) {
            transformations(
                CircleCropTransformation()
            )
        }
        binding.ivFourSelectorPetThird.load(fourSelectorViewModel.petImageList.value?.get(2)) {
            transformations(
                CircleCropTransformation()
            )
        }
        binding.ivFourSelectorPetFourth.load(fourSelectorViewModel.petImageList.value?.get(3)) {
            transformations(
                CircleCropTransformation()
            )
        }
    }
}
