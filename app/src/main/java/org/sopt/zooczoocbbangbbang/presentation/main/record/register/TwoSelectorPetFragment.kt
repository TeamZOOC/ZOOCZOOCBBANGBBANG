package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentTwoSelectorPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.register.TwoSelectorPetViewModel

open class TwoSelectorPetFragment :
    BindingFragment<FragmentTwoSelectorPetBinding>(R.layout.fragment_two_selector_pet) {
    private val twoSelectorViewModel: TwoSelectorPetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = twoSelectorViewModel
        super.onViewCreated(view, savedInstanceState)
        showPetInfo()
        checkIsSelected()
        clickRecordBtn()
    }

    private fun showPetInfo() {
        twoSelectorViewModel.getPetInfo()
        // Log.d("TwoSelector", "aaa ${twoSelectorViewModel.petNameList.value?.get(0)}")
        // Log.d("TwoSelector", "aaa ${twoSelectorViewModel.petNameList.value?.get(1)}")
        // twoSelectorViewModel.petNum.observe(viewLifecycleOwner) {
        //     binding.tvTwoSelectorPetFirst.text = twoSelectorViewModel.petNameList.value?.get(0)
        //     binding.tvTwoSelectorPetFirst.text = twoSelectorViewModel.petNameList.value?.get(1)
        // }

        // binding.tvTwoSelectorPetFirst.text = twoSelectorViewModel.petNameList.value?.get(0)
        // binding.tvTwoSelectorPetFirst.text = twoSelectorViewModel.petNameList.value?.get(1)
    }

    private fun clickFirstSelectorItem() {
        binding.clTwoSelectorPetFirstLayout.setOnClickListener {
            twoSelectorViewModel.switchFirstBooleanValue()
            if (twoSelectorViewModel.isSelectedFirst.value == true) {
                binding.ivTwoSelectorPetFirst.setImageResource(
                    R.drawable.shape_round_green
                )
                binding.tvTwoSelectorPetFirst.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.ivTwoSelectorPetFirst.setImageResource(
                    R.drawable.shape_round_grey
                )
                binding.tvTwoSelectorPetFirst.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickSecondSelectorItem() {
        binding.clTwoSelectorPetSecondLayout.setOnClickListener {
            twoSelectorViewModel.switchSecondBooleanValue()
            if (twoSelectorViewModel.isSelectedSecond.value == true) {
                binding.ivTwoSelectorPetSecond.setImageResource(
                    R.drawable.shape_round_green
                )
                binding.tvTwoSelectorPetSecond.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.ivTwoSelectorPetSecond.setImageResource(
                    R.drawable.shape_round_grey
                )
                binding.tvTwoSelectorPetSecond.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun checkIsSelected() {
        twoSelectorViewModel.isSelectedFirst.observe(viewLifecycleOwner) {
            Log.d("TwoSelector", "isSelected::: ${twoSelectorViewModel.isSelectedFirst.value}")
            clickFirstSelectorItem()
        }
        twoSelectorViewModel.isSelectedSecond.observe(viewLifecycleOwner) {
            Log.d("TwoSelector", "isSelected::: ${twoSelectorViewModel.isSelectedSecond.value}")
            clickSecondSelectorItem()
        }
    }

    private fun clickRecordBtn() {
        binding.btnTwoSelectorPetBottom.setOnClickListener {
            activity?.let {
                val intent = Intent(context, RecordDoneActivity::class.java)
                startActivity(intent)
            }
        }
    }

    companion object {
    }
}
