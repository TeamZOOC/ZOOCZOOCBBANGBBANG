package org.sopt.zooczoocbbangbbang.data.remote.entity.detail.response

import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Comment

data class ResponseCommentsDto(
    val data: List<Comment>,
    val message: String,
    val status: Int,
    val success: Boolean
)
