package org.sopt.zooczoocbbangbbang.presentation.onboarding.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.util.Event

class PetRegisterViewModel : ViewModel() {
    private val _addPetFormEventStream: MutableLiveData<Event<Unit>> = MutableLiveData()
    val addPetFormEventStream: LiveData<Event<Unit>> get() = _addPetFormEventStream

    private val _delPetFormEventStream: MutableLiveData<Event<Int>> = MutableLiveData()
    val delPetFormEventStream: LiveData<Event<Int>> get() = _delPetFormEventStream

    private val _canAddPetForm: MutableLiveData<Boolean> = MutableLiveData(true)
    val canAddPetForm: LiveData<Boolean> get() = _canAddPetForm

    fun addPetRegisterForm() {
        _addPetFormEventStream.value = Event(Unit)
    }

    fun removePetRegisterForm(position: Int) {
        _delPetFormEventStream.value = Event(position)
    }

    fun toggleEnableAddPetButton() {
        _canAddPetForm.value = true
    }

    fun toggleDisableAddPetButton() {
        _canAddPetForm.value = false
    }
}
