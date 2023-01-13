package org.sopt.zooczoocbbangbbang.presentation.main.record.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import retrofit2.HttpException
import retrofit2.await
import timber.log.Timber

class FourSelectorPetViewModel : ViewModel() {
    val isSelectedFirst = MutableLiveData(false)
    val isSelectedSecond = MutableLiveData(false)
    val isSelectedThird = MutableLiveData(false)
    val isSelectedFourth = MutableLiveData(false)
    var petNum: MutableLiveData<Int> = MutableLiveData()
    val isPostSuccess = MutableLiveData(false)
    private val service = ServiceFactory.zoocService

    // var petName: MutableLiveData<String> = MutableLiveData()
    var petNameList = MutableLiveData<MutableList<String>>()
    var petImageList = MutableLiveData<MutableList<String>>()
    val petIdList = mutableListOf<Int>()
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    val selectedPetList = MutableLiveData<MutableList<String>>()

    fun switchFirstBooleanValue() {
        isSelectedFirst.value = !isSelectedFirst.value!!
    }

    fun switchSecondBooleanValue() {
        isSelectedSecond.value = !isSelectedSecond.value!!
    }

    fun switchThirdBooleanValue() {
        isSelectedThird.value = !isSelectedThird.value!!
    }

    fun switchFourthBooleanValue() {
        isSelectedFourth.value = !isSelectedFourth.value!!
    }

    val buttonValidation = MediatorLiveData<Boolean>().apply {
        addSource(isSelectedFirst) {
            this.value = isButtonActive()
        }
        addSource(isSelectedSecond) {
            this.value = isButtonActive()
        }
        addSource(isSelectedThird) {
            this.value = isButtonActive()
        }
        addSource(isSelectedFourth) {
            this.value = isButtonActive()
        }
    }

    private fun isButtonActive(): Boolean {
        return (
            (isSelectedFirst.value == true) or (isSelectedSecond.value == true) or (isSelectedThird.value == true) or (isSelectedFourth.value == true)
            )
    }

    fun getPetInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceFactory.zoocService.getAllPets(1).await()
            }.onSuccess {
                Timber.tag("Four").d("펫 데이터 length::: %s", it.data.size)
                Timber.tag("Four").d(it.data[0].name)
                Timber.tag("Four").d(it.data[1].name)
                val len: Int = it.data.size
                for (i in 0 until len) {
                    petNameList.value?.add(it.data[i].name)
                    petImageList.value?.add(it.data[i].photo)
                    petIdList.add(it.data[i].id)
                    Log.d("Four", "${it.data[i].name}")
                    Log.d("Four", "${it.data[i].photo}")
                }
            }.onFailure {
                if (it is HttpException) {
                    Timber.tag("Four").e("모든 펫 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Timber.tag("Four").e("모든 펫 가져오기 서버 통신 onFailure")
                }
            }
        }
    }

    // private fun onSubmit() {
    //     val requestBody = json.encodeToString(PetInfo(text.value!!, petInfo.value!!))
    //         .toRequestBody("application/body".toMediaType())
    //
    //     viewModelScope.launch {
    //         runCatching {
    //             service.postRecord(
    //                 "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTY3MjkwMjQzOSwiZXhwIjoxNjczNTA3MjM5fQ.ztLfFDHWIQP-vpejw_hfCwZPbkR5FjFMy7F6MRMbrZQ",
    //                 image.value?.toFormData(),
    //                 requestBody
    //             )
    //         }.onSuccess {
    //             isPostSuccess.value = true
    //         }.onFailure {
    //             isPostSuccess.value = false
    //             _errorMessage.value = "네트워크 상태가 좋지 않습니다"
    //             Timber.tag("RecordViewModel").d(errorMessage.toString())
    //         }
    //     }
    // }
    //
    // @kotlinx.serialization.Serializable
    // data class PetInfo(
    //     val content: String,
    //     val pet: List<String>
    // )
}
