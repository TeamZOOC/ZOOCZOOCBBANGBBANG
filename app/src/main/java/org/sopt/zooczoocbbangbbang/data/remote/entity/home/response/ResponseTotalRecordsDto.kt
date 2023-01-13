package org.sopt.zooczoocbbangbbang.data.remote.entity.home.response

import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Comment

data class ResponseTotalRecordsDto(
    val data: List<RecordDto>,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class RecordDto(
        val comments: List<Comment>,
        val record: Record
    ) {
        data class Record(
            val content: String,
            val date: String,
            val id: Int,
            val photo: String,
            val writerName: String,
            val writerPhoto: String?
        )
    }
}
