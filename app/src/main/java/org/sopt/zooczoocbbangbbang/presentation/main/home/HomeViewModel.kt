package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponsePetDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseTotalRecordsDto
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.ArchivePostingData
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.PetData
import retrofit2.HttpException
import retrofit2.await

class HomeViewModel : ViewModel() {
    private val zoocService = ServiceFactory.zoocService
    private val pets = MutableLiveData<List<PetData>>()
    private val records = MutableLiveData<List<ArchivePostingData>>()

    fun getRecords() {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.getAllRecords(1).await()
            }.onSuccess {
                records.value = mappingRecord(it)
            }.onFailure {
                if (it is HttpException) {
                    Log.e("HomeFragment", "전체 기록 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("HomeFragment", "전체 기록 가져오기 서버 통신 onFailure")
                }
            }
        }
    }

    fun getPets() {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.getAllPets(1).await()
            }.onSuccess {
                pets.value = mappingPet(it)
            }.onFailure {
                if (it is HttpException) {
                    Log.e("HomeFragment", "모든 펫 가져오기 서버 통신 onResponse but not successful")
                } else {
                    Log.e("HomeFragment", "모든 펫 가져오기 서버 통신 onFailure")
                }
            }
        }
    }

    private fun mappingRecord(data: ResponseTotalRecordsDto): List<ArchivePostingData> {
        return data.data.map {
            ArchivePostingData(
                commentWriters = it.commentWriters,
                record = it.record,
                isSelected = false
            )
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
