package org.sopt.zooczoocbbangbbang.data.remote.entity.home.response

import com.google.gson.annotations.SerializedName

data class ResponseFamilyInfoDto(
    val data: List<FamilyInfo>,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class FamilyInfo(
        val code: String,
        @SerializedName("created_at")
        val createdAt: String,
        val id: Int,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}
