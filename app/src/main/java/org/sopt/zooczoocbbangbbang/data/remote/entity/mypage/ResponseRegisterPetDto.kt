package org.sopt.zooczoocbbangbbang.data.remote.entity.mypage

data class ResponseRegisterPetDto(
    val data: List<Data>,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val id: Int,
        val name: String,
        val photo: String
    )
}
