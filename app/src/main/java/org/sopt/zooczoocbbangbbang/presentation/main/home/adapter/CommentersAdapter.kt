package org.sopt.zooczoocbbangbbang.presentation.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.databinding.ItemCommentersBinding
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.ArchivePostingData

class CommentersAdapter(private val commenters: List<ArchivePostingData.CommentWriter>) :
    RecyclerView.Adapter<CommentersAdapter.CommentersViewHolder>() {

    class CommentersViewHolder(private val binding: ItemCommentersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ArchivePostingData.CommentWriter) {
            binding.ivCommenter.load(data.writerPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentersBinding.inflate(layoutInflater, parent, false)
        return CommentersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentersViewHolder, position: Int) {
        holder.onBind(commenters[position])
    }

    override fun getItemCount(): Int = commenters.size
}
