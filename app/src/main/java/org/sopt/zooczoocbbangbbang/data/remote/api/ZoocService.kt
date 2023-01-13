package org.sopt.zooczoocbbangbbang.data.remote.api

import org.sopt.zooczoocbbangbbang.data.remote.entity.alarm.ResponseAlarmDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.request.RequestCommentDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.request.RequestEmojiDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseCommentsDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseRecordDetailDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseFamilyInfoDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponsePetDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseTotalRecordsDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ZoocService {
    @GET("family/mypage")
    fun getMemberList(): Call<ResponseMembersDto>

    @GET("family")
    fun getFamilyInfo(): Call<ResponseFamilyInfoDto>

    @GET("record/aos/{familyId}/{petId}")
    fun getAllRecords(
        @Path("familyId") familyId: Int,
        @Path("petId") petId: Int
    ): Call<ResponseTotalRecordsDto>

    @GET("record/pet/{familyId}")
    fun getAllPets(
        @Path("familyId") familyId: Int
    ): Call<ResponsePetDto>

    @GET("record/{familyId}/{recordId}")
    fun getRecordDetail(
        @Path("familyId") familyId: Int,
        @Path("recordId") recordId: Int
    ): Call<ResponseRecordDetailDto>

    @POST("comment/{recordId}")
    fun postTextComment(
        @Path("recordId") recordId: Int,
        @Body body: RequestCommentDto
    ): Call<ResponseCommentsDto>

    @POST("comment/emoji/{recordId}")
    fun postEmojiComment(
        @Path("recordId") recordId: Int,
        @Body body: RequestEmojiDto
    ): Call<ResponseCommentsDto>

    @POST("user/kakao/signin")
    fun postToken(): Call<ResponseSignUpDto>

    @GET("alarm/list")
    fun getAlarms(
        @Query("familyId") familyId: Int
    ): Call<ResponseAlarmDto>
}
