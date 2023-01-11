package org.sopt.zooczoocbbangbbang.presentation.main.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.databinding.ItemGridArchivePostingBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemLinearArchivePostingBinding
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.ArchivePostingData

abstract class ArchivePostingViewHolder(private val binding: View) :
    RecyclerView.ViewHolder(binding) {
    abstract fun onBind(data: ArchivePostingData, clickItem: () -> Unit)
}

class ArchivePostingLinearViewHolder(private val binding: ItemLinearArchivePostingBinding) :
    ArchivePostingViewHolder(binding.root) {

    private lateinit var commentersAdapter: CommentersAdapter

    override fun onBind(data: ArchivePostingData, clickItem: () -> Unit) {
        binding.ivArchivePetImage.load(data.record.photo)
        binding.ivArchiveUploaderProfile.load(data.record.writerPhoto)
        binding.data = data
        initAdapter(data.commentWriters)

        if (data.isSelected) {
            expandItem()
        } else {
            foldItem()
        }

        itemView.setOnClickListener {
            clickItem()
        }
    }

    private fun initAdapter(commenters: List<ArchivePostingData.CommentWriter>) {
        commentersAdapter = CommentersAdapter(commenters)
        binding.rvCommenters.adapter = commentersAdapter
    }

    private fun expandItem() {
        binding.mlArchivePosting.transitionToEnd()
    }

    private fun foldItem() {
        binding.mlArchivePosting.transitionToStart()
    }
}

class ArchivePostingGridViewHolder(private val binding: ItemGridArchivePostingBinding) :
    ArchivePostingViewHolder(binding.root) {
    override fun onBind(data: ArchivePostingData, clickItem: () -> Unit) {
        binding.ivArchiveGridPetImage.load(data.record.photo)
    }
}
