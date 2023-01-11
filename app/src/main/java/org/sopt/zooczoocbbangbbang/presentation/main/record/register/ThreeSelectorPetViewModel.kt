package org.sopt.zooczoocbbangbbang.presentation.main.record

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThreeSelectorPetViewModel : ViewModel() {
    val isSelectedFirst = MutableLiveData(false)
    val isSelectedSecond = MutableLiveData(false)
    val isSelectedThird = MutableLiveData(false)

    fun switchFirstBooleanValue() {
        isSelectedFirst.value = !isSelectedFirst.value!!
    }

    fun switchSecondBooleanValue() {
        isSelectedSecond.value = !isSelectedSecond.value!!
    }

    fun switchThirdBooleanValue() {
        isSelectedThird.value = !isSelectedThird.value!!
    }

    val buttonValidation = MediatorLiveData<Boolean>().apply {
        addSource(isSelectedFirst) {
            this.value = isButtonActive()
        }
        addSource(isSelectedSecond) {
            this.value = isButtonActive()
        }
        addSource(isSelectedThird) {
            this.value = isButtonActive()
        }
    }

    private fun isButtonActive(): Boolean {
        return (
            (isSelectedFirst.value == true) or (isSelectedSecond.value == true) or (isSelectedThird.value == true)
            )
    }
}
