package org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.daily

import android.net.Uri
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DailyViewModel : ViewModel() {
    val imageUrl = MutableLiveData<Uri>(null)
    val isImageEmpty = Transformations.map(imageUrl) { it == null }

    val textContent = MutableLiveData<String>("")
    private val isTextEmpty = Transformations.map(textContent) { it.isNullOrEmpty() }

    val buttonValidation = MediatorLiveData<Boolean>().apply {
        addSource(isImageEmpty) { value = isButtonActive() }
        addSource(isTextEmpty) { value = isButtonActive() }
    }

    private fun isButtonActive(): Boolean {
        return ((isImageEmpty.value == false) and (isTextEmpty.value == false))
    }
}
