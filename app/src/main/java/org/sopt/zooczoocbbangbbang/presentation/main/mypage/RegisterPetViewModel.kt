package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.data.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody

class RegisterPetViewModel : ViewModel() {
    private val zoocService = ServiceFactory.zoocService

    val file = MutableLiveData<ContentUriRequestBody>()
    val inputText = MutableLiveData("")

    val _registerPetData = MutableLiveData<List<ResponseMembersDto.Data.Pet>>()
    val registerData: LiveData<List<ResponseMembersDto.Data.Pet>>
        get() = _registerPetData

    @kotlinx.serialization.Serializable
    private data class PetIntroduction(
        val name: String
    )
}
