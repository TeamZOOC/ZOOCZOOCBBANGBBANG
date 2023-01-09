package org.sopt.zooczoocbbangbbang.data

import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ZoocService {
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjYsImlhdCI6MTY3Mjk0NTE3MSwiZXhwIjoxNjczNTQ5OTcxfQ.u0wbFcohn0YlmGW_AeAq1I3LSC-y3oUtFHp9vGA_vbw")
    @GET("family/mypage")
    fun getUser(): Call<ResponseMembersDto>

    @GET("family/mypage")
    fun getMemberList(): Call<ResponseMembersDto>
}
