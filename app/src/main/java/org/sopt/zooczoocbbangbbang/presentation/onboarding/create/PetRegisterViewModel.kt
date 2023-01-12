package org.sopt.zooczoocbbangbbang.presentation.onboarding.create

import android.util.Log
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.presentation.onboarding.create.model.PetUiModel
import org.sopt.zooczoocbbangbbang.util.NonNullLiveData
import org.sopt.zooczoocbbangbbang.util.NonNullMutableLiveData

class PetRegisterViewModel : ViewModel() {
    private val petRegisterForms = mutableListOf(PetUiModel.empty)
    private val _petUiModelList: NonNullMutableLiveData<List<PetUiModel>> = NonNullMutableLiveData(
        listOf(PetUiModel.empty)
    )
    val petUiModelList: NonNullLiveData<List<PetUiModel>> get() = _petUiModelList

    private val _canAddForm: NonNullMutableLiveData<Boolean> = NonNullMutableLiveData(true)
    val canAddForm: NonNullLiveData<Boolean> get() = _canAddForm

    fun addPetRegisterForm() {
        if (!_canAddForm.value) return
        petRegisterForms.add(PetUiModel.empty)
        updateForms()
        Log.d("testui", "$petRegisterForms")
    }

    fun removePetRegisterForm(position: Int) {
        if (petRegisterForms.size == 1) return
        petRegisterForms.removeAt(position)
        updateForms()
    }

    fun editPetRegisterForm(position: Int, uriString: String, name: String) {
        petRegisterForms[position].apply {
            this.uriString = uriString
            this.name = name
        }
    }

    private fun updateForms() {
        _petUiModelList.value = petRegisterForms.toList()
    }

    fun toggleEnableAddFrom() {
        _canAddForm.value = true
    }

    fun toggleDisableAddFrom() {
        _canAddForm.value = false
    }
}
