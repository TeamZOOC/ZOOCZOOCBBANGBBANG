package org.sopt.zooczoocbbangbbang.data.remote.entity.home.response

data class ResponseTotalRecordsDto(
    val data: List<RecordDto>,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class RecordDto(
        val commentWriters: List<CommentWriter>,
        val record: Record
    ) {
        data class CommentWriter(
            val writerId: Int,
            val writerPhoto: String?
        )

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
