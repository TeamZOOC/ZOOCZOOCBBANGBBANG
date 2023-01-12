package org.sopt.zooczoocbbangbbang.data.remote.entity.alarm

import com.google.gson.annotations.SerializedName

data class ResponseAlarmDto(
    val data: List<Alarm>,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Alarm(
        @SerializedName("created_time")
        val createdTime: String,
        val writer: Writer
    ) {
        data class Writer(
            val id: Int,
            @SerializedName("nick_name")
            val nickName: String,
            val photo: String?
        )
    }
}
