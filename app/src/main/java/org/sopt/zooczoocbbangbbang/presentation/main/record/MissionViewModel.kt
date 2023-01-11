package org.sopt.zooczoocbbangbbang.presentation.main.record

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MissionViewModel : ViewModel() {
    val missionText = MutableLiveData("")
    private var isTextNotNull: LiveData<Boolean> =
        Transformations.map(missionText) { checkMissionText() }
    val image: MutableLiveData<Uri?> = MutableLiveData()
    private var isShowImage: LiveData<Boolean> = Transformations.map(image) { checkImage() }

    private fun checkMissionText(): Boolean {
        return !missionText.value.isNullOrEmpty()
    }

    private fun checkImage(): Boolean {
        return image.value != null
    }

    val buttonValidation = MediatorLiveData<Boolean>().apply {
        addSource(isTextNotNull) {
            this.value = isButtonActive()
        }
        addSource(isShowImage) {
            this.value = isButtonActive()
        }
    }

    private fun isButtonActive(): Boolean {
        return (
            (isTextNotNull.value == true) and (isShowImage.value == true)
            )
    }
}
