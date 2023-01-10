package org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response

import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Comment

data class ResponseRecordDetailDto(
    val data: RecordDetailDto,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class RecordDetailDto(
        val comments: List<Comment>,
        val leftId: Int?,
        val record: Record,
        val rightId: Int?
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
