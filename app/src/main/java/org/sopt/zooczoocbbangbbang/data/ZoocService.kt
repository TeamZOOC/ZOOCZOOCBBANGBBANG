package org.sopt.zooczoocbbangbbang.data

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.record.RequestRecordDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.record.ResponseMissionDto
import org.sopt.zooczoocbbangbbang.data.remote.entity.record.ResponsePetInfoDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ZoocService {
    @GET("family/mypage")
    fun getMemberList(): Call<ResponseMembersDto>

    // 펫 정보 조희 (GET)
    @GET("record/pet/{familyId}")
    fun getPetInfo(): Call<ResponsePetInfoDto>

    // 미션 정보 조회 (GET)
    @GET("record/mission/{familyId}")
    fun getMissionList(): Call<ResponseMissionDto>

    // 기록/미션 작성하기 (POST)
    @Multipart
    @POST("record/{familyId}?missionId={missionId}")
    fun postRecord(
        @Header("x-auth-token") token: String,
        @Part image: MultipartBody.Part? = null,
        @Part requestBody: RequestBody
    ): Call<RequestRecordDto>
}
