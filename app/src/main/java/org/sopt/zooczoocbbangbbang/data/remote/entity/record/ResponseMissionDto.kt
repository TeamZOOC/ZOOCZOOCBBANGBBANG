package org.sopt.zooczoocbbangbbang.data.remote.entity.record

import com.google.gson.annotations.SerializedName

data class ResponseMissionDto(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<MissionList>
) {
    data class MissionList(
        val id: Int,
        @SerializedName("mission_content")
        val missionContent: String
    )
}
