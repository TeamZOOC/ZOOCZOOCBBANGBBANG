package org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.petchoosing

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponsePetDto
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.PetData
import retrofit2.HttpException
import retrofit2.await

class PetChoosingViewModel : ViewModel() {
    private val zoocService = ServiceFactory.zoocService

    val pets = MutableLiveData<List<PetData>>()
    val petIds = MutableLiveData<List<Int>>()
    val buttonValidation = Transformations.map(petIds) { it.isNotEmpty() }

    fun getPets() {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.getAllPets(10).await()
            }.onSuccess {
                pets.value = mappingPet(it)
            }.onFailure {
                if (it is HttpException) {
                    Log.e("PetChoosingFragment", "모든 펫 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("PetChoosingFragment", "모든 펫 가져오기 서버 통신 onFailure")
                }
            }
        }
    }

    private fun mappingPet(data: ResponsePetDto): List<PetData> {
        return data.data.map {
            PetData(
                id = it.id,
                name = it.name,
                photo = it.photo,
                isSelected = false
            )
        }
    }
}
