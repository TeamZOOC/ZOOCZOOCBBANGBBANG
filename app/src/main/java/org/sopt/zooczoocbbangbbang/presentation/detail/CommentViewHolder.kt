package org.sopt.zooczoocbbangbbang.presentation.detail

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Comment
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentEmojiBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentTextBinding

abstract class CommentViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(data: Comment)
}

class CommentTextViewHolder(private val binding: ItemCommentTextBinding) :
    CommentViewHolder(binding) {
    override fun onBind(data: Comment) {
        binding.ivDetailCommentTextCommenterImage.load(data.photo)
        binding.data = data
    }
}

class CommentEmojiViewHolder(private val binding: ItemCommentEmojiBinding) :
    CommentViewHolder(binding) {
    override fun onBind(data: Comment) {
        binding.ivDetailCommentEmojiEmoji.load(Emoji.findIdByOrdinal(data.emoji?:throw IllegalArgumentException("잘못된 이모지 아이디 입니다.")))
        binding.ivDetailCommentEmojiCommenterImage.load(data.photo)
        binding.data = data
    }
}
