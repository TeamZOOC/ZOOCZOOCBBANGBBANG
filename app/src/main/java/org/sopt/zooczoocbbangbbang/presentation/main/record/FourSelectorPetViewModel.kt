package org.sopt.zooczoocbbangbbang.presentation.main.record

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FourSelectorPetViewModel : ViewModel() {
    val isSelectedFirst = MutableLiveData(false)
    val isSelectedSecond = MutableLiveData(false)
    val isSelectedThird = MutableLiveData(false)
    val isSelectedFourth = MutableLiveData(false)

    fun switchFirstBooleanValue() {
        isSelectedFirst.value = !isSelectedFirst.value!!
    }

    fun switchSecondBooleanValue() {
        isSelectedSecond.value = !isSelectedSecond.value!!
    }

    fun switchThirdBooleanValue() {
        isSelectedThird.value = !isSelectedThird.value!!
    }

    fun switchFourthBooleanValue() {
        isSelectedFourth.value = !isSelectedFourth.value!!
    }
}
