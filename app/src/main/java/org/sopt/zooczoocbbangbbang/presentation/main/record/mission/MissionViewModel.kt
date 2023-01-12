package org.sopt.zooczoocbbangbbang.presentation.main.record.mission

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
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory.zoocService
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.PetData
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import retrofit2.HttpException
import retrofit2.await
import timber.log.Timber

class MissionViewModel : ViewModel() {
    private val service = ServiceFactory.zoocService
    private val gsonService = ServiceFactory.zoocService
    val missionText = MutableLiveData("")
    private var isTextNotNull: LiveData<Boolean> =
        Transformations.map(missionText) { checkMissionText() }
    val image: MutableLiveData<ContentUriRequestBody> = MutableLiveData()
    private var isShowImage: LiveData<Boolean> = Transformations.map(image) { checkImage() }

    // private val petInfo: MutableLiveData<List<String>> = MutableLiveData(listOf())
    private val allPetList: MutableLiveData<List<String>> = MutableLiveData(mutableListOf())
    private val choosenPetList: MutableLiveData<List<String>> = MutableLiveData(listOf())
    var petNum: MutableLiveData<Int> = MutableLiveData()

    private val _pets = MutableLiveData<List<PetData>>()
    val pets: LiveData<List<PetData>> get() = _pets

    val isSuccess = MutableLiveData(false)
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

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

    private fun onSubmit() {
        val requestBody = json.encodeToString(PetInfo(missionText.value!!, allPetList.value!!))
            .toRequestBody("application/body".toMediaType())

        viewModelScope.launch {
            runCatching {
                service.postRecord(
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTY3MjkwMjQzOSwiZXhwIjoxNjczNTA3MjM5fQ.ztLfFDHWIQP-vpejw_hfCwZPbkR5FjFMy7F6MRMbrZQ",
                    image.value?.toFormData(),
                    requestBody
                )
            }.onSuccess {
                isSuccess.value = true
            }.onFailure {
                isSuccess.value = false
                _errorMessage.value = "네트워크 상태가 좋지 않습니다"
                Timber.tag("RecordViewModel").d(errorMessage.toString())
            }
        }
    }

    fun getPetNum() {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.getAllPets(9).await()
            }.onSuccess {
                Timber.tag("MissionViewModel").d("펫 데이터 length::: %s", it.data.size)
                Timber.tag("MissionViewModel").d(it.data[0].name)
                Timber.tag("MissionViewModel").d(it.data[1].name)
                petNum.value = it.data.size
            }.onFailure {
                if (it is HttpException) {
                    Timber.tag("HomeFragment").e("모든 펫 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Timber.tag("HomeFragment").e("모든 펫 가져오기 서버 통신 onFailure")
                }
            }
        }
    }

    @kotlinx.serialization.Serializable
    data class PetInfo(
        val content: String,
        val allPetList: List<String>
    )
}
