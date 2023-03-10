package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Pet
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.util.enqueueUtil
import timber.log.Timber

class MemberViewModel : ViewModel() {
    private val ZoocService = ServiceFactory.zoocService

    val _memberData = MutableLiveData<List<ResponseMembersDto.Data.FamilyMember>>()
    val memberData: LiveData<List<ResponseMembersDto.Data.FamilyMember>>
        get() = _memberData
    val _userData = MutableLiveData<ResponseMembersDto.Data.User>()
    val userData: LiveData<ResponseMembersDto.Data.User>
        get() = _userData
    val _petData = MutableLiveData<List<Pet>>()
    val petData: LiveData<List<Pet>>
        get() = _petData

    fun fetchMyPageData() {
        ZoocService.getUser().enqueueUtil(
            { result ->
                _userData.value = result.data.user
                _memberData.value = result.data.familyMember
                _petData.value = result.data.pet
                Timber.d("유저 데이터 조회 성공: $result")
                Log.d("aaa", "success data: $result")
            },
            { code ->
                Timber.d("유저 데이터 조회 실패: $code")
                Log.d("aaa", "failure code: $code")
            }
        )
    }
}
