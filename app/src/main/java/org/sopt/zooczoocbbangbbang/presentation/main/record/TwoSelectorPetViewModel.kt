package org.sopt.zooczoocbbangbbang.presentation.main.record

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
}
