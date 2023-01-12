package org.sopt.zooczoocbbangbbang.data.remote.entity.auth

data class ResponseSignUpDto(
    val data: Token,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Token(
        val jwtToken: String
    )
}
