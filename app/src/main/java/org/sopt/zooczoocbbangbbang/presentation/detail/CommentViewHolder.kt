package org.sopt.zooczoocbbangbbang.presentation.detail

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentEmojiBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentTextBinding
import org.sopt.zooczoocbbangbbang.domain.detail.Comment

abstract class CommentViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(data: Comment)
}

class CommentTextViewHolder(private val binding: ItemCommentTextBinding) :
    CommentViewHolder(binding) {
    override fun onBind(data: Comment) {
        binding.ivDetailCommentTextCommenterImage.load(data.profileImage)
        binding.data = data
    }
}

class CommentEmojiViewHolder(private val binding: ItemCommentEmojiBinding) :
    CommentViewHolder(binding) {
    override fun onBind(data: Comment) {
        binding.ivDetailCommentEmojiEmoji.load(Emoji.findIdByOrdinal(data.emojiOrdinal))
        binding.ivDetailCommentEmojiCommenterImage.load(data.profileImage)
        binding.data = data
    }
}
