package org.sopt.zooczoocbbangbbang.data

import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import retrofit2.Call
import retrofit2.http.GET

interface ZoocService{
    @GET("family/mypage")
    fun getMemberList(): Call<ResponseMembersDto>
}
