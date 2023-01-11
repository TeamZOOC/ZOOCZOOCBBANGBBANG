package org.sopt.zooczoocbbangbbang.presentation.main.record.daily

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.zooczoocbbangbbang.data.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.ServiceFactory.json
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import timber.log.Timber

class RecordViewModel : ViewModel() {
    private val service = ServiceFactory.zoocService
    val recordText = MutableLiveData("")
    private var isTextNotNull: LiveData<Boolean> = Transformations.map(recordText) { checkText() }
    val image: MutableLiveData<ContentUriRequestBody> = MutableLiveData()
    private var isShowImage: LiveData<Boolean> = Transformations.map(image) { checkImage() }
    private val petInfo: MutableLiveData<List<String>> = MutableLiveData(listOf("1", "2", "3"))

    val isRecordPostSuccess = MutableLiveData(false)
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private fun checkText(): Boolean {
        return !recordText.value.isNullOrEmpty()
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
        return (isTextNotNull.value == true) and (isShowImage.value == true)
    }

    private fun onSubmit() {
        val requestBody = json.encodeToString(PetInfo(recordText.value!!, petInfo.value!!))
            .toRequestBody("application/body".toMediaType())

        viewModelScope.launch {
            runCatching {
                service.postRecord(
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTY3MjkwMjQzOSwiZXhwIjoxNjczNTA3MjM5fQ.ztLfFDHWIQP-vpejw_hfCwZPbkR5FjFMy7F6MRMbrZQ",
                    image.value?.toFormData(),
                    requestBody
                )
            }.onSuccess {
                isRecordPostSuccess.value = true
            }.onFailure {
                isRecordPostSuccess.value = false
                _errorMessage.value = "네트워크 상태가 좋지 않습니다"
                Timber.tag("RecordViewModel").d(errorMessage.toString())
            }
        }
    }

    @kotlinx.serialization.Serializable
    data class PetInfo(
        val content: String,
        val pet: List<String>
    )
}
