package org.sopt.zooczoocbbangbbang.data.remote.entity.mypage

data class ResponseEditProfileDto(
    val data: Profile,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Profile(
        val id: Int,
        val nick_name: String,
        val photo: String
    )
}
