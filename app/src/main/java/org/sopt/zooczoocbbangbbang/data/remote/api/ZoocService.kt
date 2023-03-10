package org.sopt.zooczoocbbangbbang.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.zooczoocbbangbbang.data.remote.entity.alarm.ResponseAlarmDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.auth.ResponseSignUpDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.request.RequestCommentDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.request.RequestEmojiDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseCommentsDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseDeleteDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseRecordDetailDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseFamilyInfoDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponsePetDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseTotalRecordsDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseEditProfileDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.record.RequestRecordDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.record.ResponseMissionDto
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.ResponseYmRecordDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ZoocService {
    @GET("family/mypage")
    fun getUser(): Call<ResponseMembersDto>

    @Multipart
    @PATCH("user/profile")
    fun editProfile(
        @Query("photo") photo: Boolean,
        @Part("nickName") body: RequestBody,
        @Part file: MultipartBody.Part? = null
    ): Call<ResponseEditProfileDto>

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

    @GET("record/detail/{familyId}/{petId}/{recordId}")
    fun getRecordDetail(
        @Path("familyId") familyId: Int,
        @Path("petId") petId: Int,
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

    // ?????? ?????? ?????? (GET)
    @GET("record/mission/{familyId}")
    fun getMissionList(
        @Path("familyId") familyId: Int
    ): Call<ResponseMissionDto>

    // ??????/?????? ???????????? (POST)
    @Multipart
    @POST("record/{familyId}")
    fun postMission(
        @Query("missionId") missionId: Int,
        @Part file: MultipartBody.Part? = null,
        @Part("content") content: RequestBody,
        @Part("pet") pet: RequestBody
        // @Header("x-auth-token") token: String,
        /*@Part file: MultipartBody.Part? = null,
        @Part requestBody: RequestBody*/
    ): Call<RequestRecordDto>

    @Multipart
    @POST("record/{familyId}")
    fun postRecord(
        @Path("familyId") familyId: Int,
        @Part file: MultipartBody.Part? = null,
        @Part("content") content: RequestBody,
        @Part("pet") pet: RequestBody
        // @Header("x-auth-token") token: String,
        /*@Part file: MultipartBody.Part? = null,
        @Part requestBody: RequestBody*/
    ): Call<RequestRecordDto>

    @Multipart
    @POST("record/{familyId}")
    fun postYmRecord(
        @Path("familyId") familyId: Int,
        @Part("content") contentBody: RequestBody,
        @Part file: MultipartBody.Part? = null,
        @Part pet: List<MultipartBody.Part>
    ): Call<ResponseYmRecordDto>

    @DELETE("record/{recordId}")
    fun deleteRecord(
        @Path("recordId") recordId: Int
    ): Call<ResponseDeleteDto>
}
