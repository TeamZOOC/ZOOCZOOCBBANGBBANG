package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class EditProfileViewModel : ViewModel() {
    Transformations.map(inputText)
    { checkEditText() }

    private fun checkEditText(): Boolean {
        return !inputText.value.isNullOrEmpty()
    }

    private fun isButtonActive(): Boolean {
        return (isTextNotNull.value == true)
    }

    val buttonValidation: LiveData<Boolean> = Transformations.map(isTextNotNull) {
        isButtonActive()
    }
}
