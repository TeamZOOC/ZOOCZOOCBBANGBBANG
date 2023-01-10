package org.sopt.zooczoocbbangbbang.domain.detail

data class Comment(
    val isEmoji: Boolean,
    val emojiOrdinal: Int,
    val profileImage: Int,
    val editor: String,
    val content: String,
    val date: String
)
