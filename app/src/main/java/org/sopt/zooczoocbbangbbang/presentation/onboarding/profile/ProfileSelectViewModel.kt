package org.sopt.zooczoocbbangbbang.presentation.onboarding.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.util.Event

class ProfileSelectViewModel : ViewModel() {
    private val _popUpProfilePickerEventStream: MutableLiveData<Event<Unit>> = MutableLiveData()
    val popUpProfilePickerEventStream: LiveData<Event<Unit>> get() = _popUpProfilePickerEventStream

    private val _profileImageStream: MutableLiveData<String> = MutableLiveData()
    val profileImageStream: LiveData<String> get() = _profileImageStream

    fun onClickProfilePickerButton() {
        _popUpProfilePickerEventStream.value = Event(Unit)
    }

    fun onSaveProfileImage(uriString: String) {
        Log.d("tests", uriString)
        _profileImageStream.value = uriString
    }
}
