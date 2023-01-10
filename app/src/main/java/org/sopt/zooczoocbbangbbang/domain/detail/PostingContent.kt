package org.sopt.zooczoocbbangbbang.domain.detail

data class PostingContent(
    val image: Int,
    val date: String,
    val content: String,
    val editor: Editor,
    val previousId: Int,
    val nextId: Int
) {
    data class Editor(
        val image: Int,
        val name: String
    )
}
