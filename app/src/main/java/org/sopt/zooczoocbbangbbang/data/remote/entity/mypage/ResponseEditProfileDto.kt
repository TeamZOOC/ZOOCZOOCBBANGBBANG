package org.sopt.zooczoocbbangbbang.data.remote.entity.mypage

import com.google.gson.annotations.SerializedName

data class ResponseEditProfileDto(
    val data: Profile,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val id: Int,
        @SerializedName("nick_name")
        val nickName: String,
        val photo: String
    )
}
