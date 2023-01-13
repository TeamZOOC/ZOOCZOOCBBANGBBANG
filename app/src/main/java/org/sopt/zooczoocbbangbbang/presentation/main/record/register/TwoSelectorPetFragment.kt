package org.sopt.zooczoocbbangbbang.presentation.main.record.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentTwoSelectorPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordDoneActivity
import timber.log.Timber

open class TwoSelectorPetFragment :
    BindingFragment<FragmentTwoSelectorPetBinding>(R.layout.fragment_two_selector_pet) {
    private val twoSelectorViewModel: TwoSelectorPetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = twoSelectorViewModel
        super.onViewCreated(view, savedInstanceState)
        twoSelectorViewModel.getPetInfo()
        fetchPetImage()
        checkIsSelected()
        clickRecordBtn()
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

    private fun clickRecordBtn() {
        binding.btnTwoSelectorPetBottom.setOnClickListener {
            activity?.let {
                val intent = Intent(context, RecordDoneActivity::class.java)
                startActivity(intent)
            }
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

    companion object {
    }
}
