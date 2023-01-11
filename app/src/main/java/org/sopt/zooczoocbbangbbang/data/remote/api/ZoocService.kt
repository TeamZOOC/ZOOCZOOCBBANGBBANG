package org.sopt.zooczoocbbangbbang.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.request.RequestCommentDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.request.RequestEmojiDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseCommentsDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response.ResponseRecordDetailDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseFamilyInfoDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponsePetDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseTotalRecordsDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseEditProfileDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ZoocService {
    @GET("family/mypage")
    fun getMemberList(): Call<ResponseMembersDto>

    @GET("family")
    fun getFamilyInfo(): Call<ResponseFamilyInfoDto>

    @GET("record/{familyId}")
    fun getAllRecords(
        @Path("familyId") familyId: Int
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

    @Multipart
    @PATCH("user/profile")
    fun patchEditProfile(
        @Query("photo") photo: Boolean,
        @Part file: MultipartBody.Part?,
        @Part body: RequestBody
    ): Call<ResponseEditProfileDto>
}
