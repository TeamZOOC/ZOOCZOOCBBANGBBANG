package org.sopt.zooczoocbbangbbang.presentation.main.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class RecordViewModel : ViewModel() {

    val recordText: MutableLiveData<String> = MutableLiveData("")

    private val isTextNotNull: LiveData<Boolean> = Transformations.map(recordText) { checkText() }
    val image: MutableLiveData<Int> =
        MutableLiveData(0) // Q: 이미지는 받아올 때 어떤 형식으로 받아와서 live data를 사용해야 하는지?

    var isShowImage: LiveData<Boolean> = Transformations.map(image) { checkImage() }
    val isButtonActive = MutableLiveData(true) // 수정해야함

    private fun checkText(): Boolean {
        return if (recordText.value != "") {
            true
        } else {
            true
        }
    }

    private fun checkImage(): Boolean {
        return if (image.value != 0) {
            true
        } else {
            true
        }
    }
}
