package org.sopt.zooczoocbbangbbang.data.remote.entity.mypage

import com.google.gson.annotations.SerializedName

data class ResponseMembersDto(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val familyMember: List<FamilyMember>,
        val pet: List<Pet>,
        val user: User
    ) {
        data class FamilyMember(
            val id: Int,
            @SerializedName("nick_name")
            val nickName: String,
            val photo: String
        )

        data class Pet(
            val id: Int,
            val name: String,
            val photo: String
        )

        data class User(
            val id: Int,
            @SerializedName("nick_name")
            val memberName: String,
            @SerializedName("photo")
            val imgMember: String
        )
    }
}
