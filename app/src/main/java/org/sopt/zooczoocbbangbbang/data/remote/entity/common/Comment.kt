package org.sopt.zooczoocbbangbbang.data.remote.entity.common

data class Comment(
    val content: String?,
    val date: String,
    val emoji: Int?,
    val isEmoji: Boolean,
    val nickName: String,
    val photo: String?
)