package org.sopt.zooczoocbbangbbang.data

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
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseRegisterPetDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ZoocService {
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE3LCJpYXQiOjE2NzM0NjMzMTIsImV4cCI6MTY3NDA2ODExMn0.9ZTqlzqI4jDsSnO9yHPP2pzOjmDRqs17KaQkm3yHr1c")
    @GET("family/mypage")
    fun getUser(): Call<ResponseMembersDto>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjE3LCJpYXQiOjE2NzM0NjMzMTIsImV4cCI6MTY3NDA2ODExMn0.9ZTqlzqI4jDsSnO9yHPP2pzOjmDRqs17KaQkm3yHr1c")
    @Multipart
    @PATCH("user/profile")
    fun editProfile(
        @Query("photo") photo: Boolean,
        @Part("nickName") body: RequestBody,
        @Part file: MultipartBody.Part? = null
    ): Call<ResponseEditProfileDto>

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
    @POST("family/pet/{familyId}")
    fun registerPet(
        @Header("x-auth-token") token: String,
        @Query("familyId") familyId: Int,
        @Part photo: MultipartBody.Part? = null,
        @Part name: RequestBody
    ): Call<ResponseRegisterPetDto>
}
