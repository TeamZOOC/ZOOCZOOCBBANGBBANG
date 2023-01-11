package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.util.enqueueUtil
import timber.log.Timber

class MemberViewModel : ViewModel() {
    private val ZoocService = ServiceFactory.zoocService
    val _memberData = MutableLiveData<List<ResponseMembersDto.Data.FamilyMember>>()
    val memberData: LiveData<List<ResponseMembersDto.Data.FamilyMember>>
        get() = _memberData

    init {
        fetchMemberRecyclerView()
    }

    private fun fetchMemberRecyclerView() {
        ZoocService.getMemberList().enqueueUtil(
            { result ->
                _memberData.value = result.data.familyMember
                Timber.i("멤버 데이터 조회 성공: $result")
            },
            { code ->
                Timber.d("멤버 데이터 조회 실패: $code")
            }
        )
    }
}
