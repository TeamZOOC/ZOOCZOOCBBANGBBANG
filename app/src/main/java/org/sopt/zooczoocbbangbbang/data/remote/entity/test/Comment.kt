package org.sopt.zooczoocbbangbbang.data.remote.entity.test

data class Comment(
    val content: String,
    val date: String,
    val emoji: Any,
    val isEmoji: Boolean,
    val nickName: String,
    val photo: String,
    val writerId: Int
)