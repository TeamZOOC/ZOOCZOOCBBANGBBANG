package org.sopt.zooczoocbbangbbang.presentation.main.mypage.editprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory.json
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseEditProfileDto
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import retrofit2.HttpException
import retrofit2.await

class EditProfileViewModel : ViewModel() {
    private val zoocService = ServiceFactory.zoocService

    val file = MutableLiveData<ContentUriRequestBody>()

    val _editProfileData = MutableLiveData<ResponseEditProfileDto.Data>()
    val editProfileData: LiveData<ResponseEditProfileDto.Data>
        get() = _editProfileData

    var setCount = MutableLiveData(0)
    val inputText = MutableLiveData("")

    val isProfileEditSuccess = MutableLiveData<Boolean>(false)

    fun countText() {
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

    val buttonValidation: LiveData<Boolean> = Transformations.map(isTextNotNull) {
        isButtonActive()
    }

    @kotlinx.serialization.Serializable
    private data class ProfileIntroduction(
        val nickName: String
    )

    fun onSubmit() {
        Log.d("aaa", "서밋 접근 성공")
        val requestBody = json.encodeToString(ProfileIntroduction(inputText.value ?: ""))
            .toRequestBody("text/plain".toMediaType())

        val requestB = (inputText.value ?: "").toRequestBody("text/plain".toMediaType())

        viewModelScope.launch {
            runCatching {
                Log.d("aaa", "사진 널 아니지: ${file.value}")
                zoocService.editProfile(file.value != null, requestB, file.value?.toFormData())
                    .await()
            }.onSuccess {
                Log.d("aaa", "성공")
                isProfileEditSuccess.value = true
            }.onFailure {
                if (it is HttpException) {
                    Log.e("aaa", "프로필 수정 서버 통신 onResponse but not successful")
                } else {
                    Log.e("aaa", "프로필 수정 서버 통신 onFailure")
                }
            }
        }
    }
}
