package org.sopt.zooczoocbbangbbang.presentation.main.home.data

import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Comment
import org.sopt.zooczoocbbangbbang.data.remote.entity.home.response.ResponseTotalRecordsDto

data class ArchivePostingData(
    val commentWriters: List<Comment>,
    val record: ResponseTotalRecordsDto.RecordDto.Record,
    var isSelected: Boolean = false
)
