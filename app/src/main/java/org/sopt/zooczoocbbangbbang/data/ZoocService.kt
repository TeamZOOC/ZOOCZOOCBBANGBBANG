package org.sopt.zooczoocbbangbbang.data

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseEditProfileDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Query

interface ZoocService {
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTY3MjkwMjQzOSwiZXhwIjoxNjczNTA3MjM5fQ.ztLfFDHWIQP-vpejw_hfCwZPbkR5FjFMy7F6MRMbrZQ")
    @GET("family/mypage")
    fun getUser(): Call<ResponseMembersDto>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTY3MjkwMjQzOSwiZXhwIjoxNjczNTA3MjM5fQ.ztLfFDHWIQP-vpejw_hfCwZPbkR5FjFMy7F6MRMbrZQ")
    @Multipart
    @PATCH("user/profile")
    fun editProfile(
        @Query("photo") photo: Boolean,
        @Part body: RequestBody,
        @Part image: MultipartBody.Part? = null
    ): Call<ResponseEditProfileDto>

    @GET("family/mypage")
    fun getMemberList(): Call<ResponseMembersDto>
}
