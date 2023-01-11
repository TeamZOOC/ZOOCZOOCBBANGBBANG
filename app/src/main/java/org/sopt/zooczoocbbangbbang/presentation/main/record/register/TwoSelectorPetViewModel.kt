package org.sopt.zooczoocbbangbbang.presentation.main.record

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TwoSelectorPetViewModel : ViewModel() {
    val isSelectedFirst = MutableLiveData(false)
    val isSelectedSecond = MutableLiveData(false)

    fun switchFirstBooleanValue() {
        isSelectedFirst.value = !isSelectedFirst.value!!
    }

    fun switchSecondBooleanValue() {
        isSelectedSecond.value = !isSelectedSecond.value!!
    }

    val buttonValidation = MediatorLiveData<Boolean>().apply {
        addSource(isSelectedFirst) {
            this.value = isButtonActive()
        }
        addSource(isSelectedSecond) {
            this.value = isButtonActive()
        }
    }

    private fun isButtonActive(): Boolean {
        return (
            (isSelectedFirst.value == true) or (isSelectedSecond.value == true)
            )
    }
}
