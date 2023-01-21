package org.sopt.zooczoocbbangbbang.presentation.main.record.daily

import android.util.Log
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
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory.json
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.PetData
import org.sopt.zooczoocbbangbbang.presentation.main.record.mission.MissionUiModel
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import retrofit2.HttpException
import retrofit2.await
import timber.log.Timber

// recordviewmodel을 반려동물 선택 fragment와 데이터를 공유해서 서버통신을 해야할듯...
class RecordViewModel : ViewModel() {
    private val service = ServiceFactory.zoocService
    val recordText = MutableLiveData("")
    private var isTextNotNull: LiveData<Boolean> = Transformations.map(recordText) { checkText() }
    val image: MutableLiveData<ContentUriRequestBody> = MutableLiveData()
    private var isShowImage: LiveData<Boolean> = Transformations.map(image) { checkImage() }
    private val petInfo: MutableLiveData<List<String>> = MutableLiveData(listOf("", "", "", ""))
    var petNum: MutableLiveData<Int> = MutableLiveData()

    val isRecordPostSuccess = MutableLiveData(false)
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    var recordList = MutableLiveData<List<MissionUiModel>>()
    val selectedPets = MutableLiveData<List<Int>>()

    val isSuccess = MutableLiveData(false)

    private val _pets = MutableLiveData<List<PetData>>()
    val pets: LiveData<List<PetData>> get() = _pets

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

    // private fun onSubmit() {
    //     val requestBody = json.encodeToString(PetInfo(recordText.value!!, petInfo.value!!))
    //         .toRequestBody("application/body".toMediaType())
    //
    //     viewModelScope.launch {
    //         runCatching {
    //             /*service.postRecord(
    //                 image.value?.toFormData(),
    //                 requestBody
    //             )*/
    //         }.onSuccess {
    //             isRecordPostSuccess.value = true
    //         }.onFailure {
    //             isRecordPostSuccess.value = false
    //             _errorMessage.value = "네트워크 상태가 좋지 않습니다"
    //             Timber.tag("RecordViewModel").d(errorMessage.toString())
    //         }
    //     }
    // }

    fun onSubmit() {
        val content = recordText.value!!.toRequestBody("text/plain".toMediaType())
        val pets = json.encodeToString(selectedPets.value!!)
            .toRequestBody("text/plain".toMediaType())

        viewModelScope.launch {
            runCatching {
                service.postRecord(
                    10,
                    image.value?.toFormData(),
                    content,
                    pets
                )
            }.onSuccess {
                Log.d("qwer", "jjj서버통신 성공")
                isSuccess.value = true
            }.onFailure {
                isSuccess.value = false
                if (it is HttpException) {
                    Log.e("qwer", "jjjjjj미션 등록하기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("qwer", it.stackTraceToString())
                }
            }
        }
    }

    fun getPetNum() {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceFactory.zoocService.getAllPets(10).await()
            }.onSuccess {
                Timber.tag("RecordViewModel").d("펫 데이터 length::: %s", it.data.size)
                Timber.tag("RecordViewModel").d(it.data[0].name)
                Timber.tag("RecordViewModel").d(it.data[1].name)
                petNum.value = it.data.size
            }.onFailure {
                if (it is HttpException) {
                    Timber.tag("RecordViewModel").e("모든 펫 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Timber.tag("RecordViewModel").e("모든 펫 가져오기 서버 통신 onFailure")
                }
            }
        }
    }

    @kotlinx.serialization.Serializable
    data class PetInfo(
        val content: String,
        val pet: List<String>
    )
}
