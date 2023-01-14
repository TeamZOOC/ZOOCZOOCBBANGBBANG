package org.sopt.zooczoocbbangbbang.presentation.main.record.register

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import retrofit2.HttpException
import retrofit2.await
import timber.log.Timber

class TwoSelectorPetViewModel : ViewModel() {
    val isSelectedFirst = MutableLiveData(false)
    val isSelectedSecond = MutableLiveData(false)
    var petNameList = MutableLiveData<List<String>>()
    var petImageList = MutableLiveData<List<String>>()
    val petIdList = mutableListOf<Int>()

    fun switchFirstBooleanValue() {
        isSelectedFirst.value = !isSelectedFirst.value!!
    }

    fun switchSecondBooleanValue() {
        isSelectedSecond.value = !isSelectedSecond.value!!
    }

    val buttonValidation = MediatorLiveData<Boolean>().apply {
        addSource(isSelectedFirst) {
            this.value = isButtonActive()
        }
        addSource(isSelectedSecond) {
            this.value = isButtonActive()
        }
    }

    private fun isButtonActive(): Boolean {
        return (
            (isSelectedFirst.value == true) or (isSelectedSecond.value == true)
            )
    }

    fun getPetInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceFactory.zoocService.getAllPets(1).await()
            }.onSuccess { it ->
                Timber.tag("TwoSelector").d("펫 데이터 length::: %s", it.data.size)
                Timber.tag("TwoSelector").d(it.data[0].photo)
                Timber.tag("TwoSelector").d(it.data[1].photo)
                Timber.tag("TwoSelector").d("전 %s", petImageList.value)
                petNameList.value = it.data.map { it.name }
                petImageList.value = it.data.map { it.photo }
                petIdList.addAll(it.data.map { it.id })
                Timber.tag("TwoSelector").d("후 %s", petImageList.value)
            }.onFailure {
                if (it is HttpException) {
                    Timber.tag("TwoSelector").e("모든 펫 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Timber.tag("TwoSelector").e("모든 펫 가져오기 서버 통신 onFailure")
                }
            }
        }
    }
    // fun onSavePet(){
    //     if(isSelectedFirst.value == true){
    //         selectedPetList.add
    //     }
    // }
}
