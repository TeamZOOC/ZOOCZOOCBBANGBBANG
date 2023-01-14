
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentThreeSelectorPetBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.ThreeSelectorPetViewModel
import org.sopt.zooczoocbbangbbang.presentation.main.record.daily.RecordViewModel
import org.sopt.zooczoocbbangbbang.presentation.main.record.mission.MissionViewModel

class ThreeSelectorPetFragment :
    BindingFragment<FragmentThreeSelectorPetBinding>(R.layout.fragment_three_selector_pet) {
    private val threeSelectorViewModel: ThreeSelectorPetViewModel by viewModels()
    private val missionViewModel: MissionViewModel by activityViewModels()
    private val recordViewModel: RecordViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = threeSelectorViewModel
        super.onViewCreated(view, savedInstanceState)
        threeSelectorViewModel.getPetInfo()
        fetchPetImage()
        checkIsSelected()
        clickRecordBtn()
    }

    private fun clickFirstSelectorItem() {
        binding.clThreeSelectorPet1.setOnClickListener {
            threeSelectorViewModel.switchFirstBooleanValue()
            if (threeSelectorViewModel.isSelectedFirst.value == true) {
                binding.tvThreeSelectorPetFirst.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvThreeSelectorPetFirst.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickSecondSelectorItem() {
        binding.clThreeSelectorPet2.setOnClickListener {
            threeSelectorViewModel.switchSecondBooleanValue()
            if (threeSelectorViewModel.isSelectedSecond.value == true) {
                binding.tvThreeSelectorPetSecond.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvThreeSelectorPetSecond.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun clickThreeSelectorItem() {
        binding.clThreeSelectorPet3.setOnClickListener {
            threeSelectorViewModel.switchThirdBooleanValue()
            if (threeSelectorViewModel.isSelectedThird.value == true) {
                binding.tvThreeSelectorPetThird.setTextColor(Color.parseColor("#42C87F"))
            } else {
                binding.tvThreeSelectorPetThird.setTextColor(Color.parseColor("#828282"))
            }
        }
    }

    private fun gatherPets() {
        val selectedPets = mutableListOf<Int>()

        if (threeSelectorViewModel.isSelectedFirst.value == true) {
            selectedPets.add(threeSelectorViewModel.petIdList[0])
        }
        if (threeSelectorViewModel.isSelectedSecond.value == true) {
            selectedPets.add(threeSelectorViewModel.petIdList[1])
        }
        if (threeSelectorViewModel.isSelectedThird.value == true) {
            selectedPets.add(threeSelectorViewModel.petIdList[2])
        }

        Log.d("qwer", "1차 진입점")
        missionViewModel.selectedPets.value = selectedPets
        recordViewModel.selectedPets.value = selectedPets
    }

    private fun checkIsSelected() {
        threeSelectorViewModel.isSelectedFirst.observe(viewLifecycleOwner) {
            Log.d("ThreeSelector", "isSelected::: ${threeSelectorViewModel.isSelectedThird.value}")
            clickFirstSelectorItem()
        }
        threeSelectorViewModel.isSelectedSecond.observe(viewLifecycleOwner) {
            Log.d("ThreeSelector", "isSelected::: ${threeSelectorViewModel.isSelectedThird.value}")
            clickSecondSelectorItem()
        }
        threeSelectorViewModel.isSelectedThird.observe(viewLifecycleOwner) {
            Log.d("ThreeSelector", "isSelected::: ${threeSelectorViewModel.isSelectedThird.value}")
            clickThreeSelectorItem()
        }
    }

    private fun clickRecordBtn() {
        binding.btnThreeSelectorPetBottom.setOnClickListener {
            // val intent = Intent(context, RecordDoneActivity::class.java)
            // startActivity(intent)
            Log.d("qwer", "다음버튼 누름")
            gatherPets()
        }
    }

    private fun fetchPetImage() {
        binding.ivThreeSelectorPetFirst.load(threeSelectorViewModel.petImageList.value?.get(0))
        binding.ivThreeSelectorPetSecond.load(threeSelectorViewModel.petImageList.value?.get(1))
        binding.ivThreeSelectorPetThird.load(threeSelectorViewModel.petImageList.value?.get(2))
    }

    companion object {
    }
}
