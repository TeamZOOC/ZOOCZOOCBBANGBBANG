package org.sopt.zooczoocbbangbbang.presentation.main.record.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentFourSelectorPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordDoneActivity
import timber.log.Timber

class FourSelectorPetFragment :
    BindingFragment<FragmentFourSelectorPetBinding>(R.layout.fragment_four_selector_pet) {
    private val fourSelectorViewModel: FourSelectorPetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = fourSelectorViewModel
        super.onViewCreated(view, savedInstanceState)
        fourSelectorViewModel.getPetInfo()
        checkIsSelected()
        clickRecordBtn()
    }

    private fun clickFirstSelectorItem() {
        binding.clFourSelectorPet1.setOnClickListener {
            fourSelectorViewModel.switchFirstBooleanValue()
            if (fourSelectorViewModel.isSelectedFirst.value == true) {
                binding.ivFourSelectorPetFirst.setBackgroundColor(
                    R.drawable.shape_round_green
                )
                binding.tvFourSelectorPetFirst.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.ivFourSelectorPetFirst.setBackgroundColor(
                    R.drawable.shape_round_grey
                )
                binding.tvFourSelectorPetFirst.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickSecondSelectorItem() {
        binding.clFourSelectorPet2.setOnClickListener {
            fourSelectorViewModel.switchSecondBooleanValue()
            if (fourSelectorViewModel.isSelectedSecond.value == true) {
                binding.ivFourSelectorPetSecond.setBackgroundColor(
                    R.drawable.shape_round_green
                )
                binding.tvFourSelectorPetSecond.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.ivFourSelectorPetSecond.setBackgroundColor(
                    R.drawable.shape_round_grey
                )
                binding.tvFourSelectorPetSecond.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickThreeSelectorItem() {
        binding.clFourSelectorPet3.setOnClickListener {
            fourSelectorViewModel.switchThirdBooleanValue()
            if (fourSelectorViewModel.isSelectedThird.value == true) {
                binding.ivFourSelectorPetThird.setBackgroundColor(
                    R.drawable.shape_round_green
                )
                binding.tvFourSelectorPetThird.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.ivFourSelectorPetThird.setBackgroundColor(
                    R.drawable.shape_round_grey
                )
                binding.tvFourSelectorPetThird.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickAllSelectorItem() {
        binding.clFourSelectorPet4.setOnClickListener {
            fourSelectorViewModel.switchFourthBooleanValue()
            if (fourSelectorViewModel.isSelectedFourth.value == true) {
                binding.ivFourSelectorPetFourth.setBackgroundColor(
                    R.drawable.shape_round_green
                )
                binding.tvFourSelectorPetFourth.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.ivFourSelectorPetFourth.setBackgroundColor(
                    R.drawable.shape_round_grey
                )
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
            val intent = Intent(requireContext(), RecordDoneActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
    }
}
