package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Pet
import retrofit2.HttpException
import retrofit2.await

class PetRegisterViewModel : ViewModel() {
    private val zoocService = ServiceFactory.zoocService
    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> get() = _pets
    val photo = MutableLiveData<Uri>()

    fun getPets() {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.getAllPets(10).await()
            }.onSuccess {
                _pets.value = it.data
            }.onFailure {
                if (it is HttpException) {
                    Log.e("HomeFragment", "모든 펫 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("HomeFragment", "모든 펫 가져오기 서버 통신 onFailure")
                }
            }
        }
    }
}
