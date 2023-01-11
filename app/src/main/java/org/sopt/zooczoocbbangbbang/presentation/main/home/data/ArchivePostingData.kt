package org.sopt.zooczoocbbangbbang.presentation.main.home.data

import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseTotalRecordsDto

data class ArchivePostingData(
    val commentWriters: List<ResponseTotalRecordsDto.RecordDto.CommentWriter>,
    val record: ResponseTotalRecordsDto.RecordDto.Record,
    var isSelected: Boolean = false
)
