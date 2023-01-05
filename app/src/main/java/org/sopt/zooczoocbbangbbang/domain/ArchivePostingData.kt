package org.sopt.zooczoocbbangbbang.domain

data class ArchivePostingData(
    val petImage: Int,
    val content: String,
    val editorImage: Int,
    val editor: String,
    val date: String,
    val commenters: List<Commenter>,
    var isSelected: Boolean = false
) {
    data class Commenter(
        val commenterImage: Int
    )
}
