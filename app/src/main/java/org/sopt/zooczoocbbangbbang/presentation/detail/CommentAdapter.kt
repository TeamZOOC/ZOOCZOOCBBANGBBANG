package org.sopt.zooczoocbbangbbang.presentation.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Comment
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentEmojiBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentTextBinding

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    private var comments: List<Comment> = emptyList()

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
        Log.d("asdf", "initComments")
        this.comments = comments.toList()
        notifyDataSetChanged()
    }

    companion object {
        const val TEXT = 0
        const val EMOJI = 1
    }
}
