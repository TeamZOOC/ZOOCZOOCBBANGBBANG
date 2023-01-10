package org.sopt.zooczoocbbangbbang.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.domain.detail.Comment
import org.sopt.zooczoocbbangbbang.domain.detail.PostingContent

class DetailViewModel : ViewModel() {
    val postingContent = MutableLiveData<PostingContent>()
    val comments = MutableLiveData<List<Comment>>()
    val emojiId = MutableLiveData<Int>()

    fun uploadComment(comment: String) {
    }

    fun uploadEmoji(emojiId: Int) {
    }
}
