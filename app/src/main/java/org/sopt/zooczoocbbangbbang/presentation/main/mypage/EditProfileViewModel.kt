package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.data.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseEditProfileDto

class EditProfileViewModel : ViewModel() {
    private val ZoocService = ServiceFactory.zoocService

    val _editProfileData = MutableLiveData<ResponseEditProfileDto.Data>()
    val editProfileData: LiveData<ResponseEditProfileDto.Data>
        get() = _editProfileData

    var setCount = MutableLiveData(0)
    val inputText = MutableLiveData("")

    fun countText(){
        setCount.value = inputText.value!!.length
    }

    private var isTextNotNull: LiveData<Boolean> =
        Transformations.map(inputText) { checkEditText() }

    private fun checkEditText(): Boolean {
        return !inputText.value.isNullOrEmpty()
    }

    private fun isButtonActive(): Boolean {
        return (isTextNotNull.value == true)
    }
    val buttonValidation : LiveData<Boolean> = Transformations.map(isTextNotNull) {
        isButtonActive()
    }
}
