package org.sopt.zooczoocbbangbbang.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentEmojiBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentTextBinding
import org.sopt.zooczoocbbangbbang.domain.detail.Comment

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    private val comments = mutableListOf<Comment>()

    override fun getItemViewType(position: Int): Int {
        return when (comments[position].isEmoji) {
            true -> EMOJI
            false -> TEXT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TEXT -> {
                val binding = ItemCommentTextBinding.inflate(layoutInflater, parent, false)
                CommentTextViewHolder(binding)
            }
            else -> {
                val binding = ItemCommentEmojiBinding.inflate(layoutInflater, parent, false)
                CommentEmojiViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    fun initComments(comments: List<Comment>) {
        this.comments.addAll(comments)
    }

    companion object {
        const val TEXT = 0
        const val EMOJI = 1
    }
}
