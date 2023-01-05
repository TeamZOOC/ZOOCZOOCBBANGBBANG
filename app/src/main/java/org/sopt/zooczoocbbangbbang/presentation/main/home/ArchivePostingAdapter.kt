package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.databinding.ItemArchivePostingBinding
import org.sopt.zooczoocbbangbbang.domain.ArchivePostingData
import org.sopt.zooczoocbbangbbang.util.GlideExt

class ArchivePostingAdapter :
    RecyclerView.Adapter<ArchivePostingAdapter.ArchivePostingViewHolder>() {
    private val archives = mutableListOf<ArchivePostingData>()
    private var currentIndex: Int = 0
    private var previousIndex: Int = -1

    class ArchivePostingViewHolder(private val binding: ItemArchivePostingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ArchivePostingData) {
            GlideExt.loadIntImage(data.petImage, binding.ivArchivePetImage)
            GlideExt.loadIntImage(data.editorImage, binding.ivArchiveUploaderProfile)
            binding.data = data
            if (data.isSelected) {
                expandItem()
                Log.d("asdf", "expand")
            } else {
                foldItem()
            }
        }

        fun updateUi(foldableUiState: FoldableUiState) {
            when (foldableUiState) {
                FoldableUiState.EXPAND -> expandItem()
                FoldableUiState.FOLD -> foldableUiState
            }
        }

        private fun expandItem() {
            binding.mlArchivePosting.transitionToEnd()
        }

        private fun foldItem() {
            binding.mlArchivePosting.transitionToStart()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchivePostingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemArchivePostingBinding.inflate(layoutInflater, parent, false)
        return ArchivePostingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArchivePostingViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            previousIndex = currentIndex
            currentIndex = position
            holder.updateUi(FoldableUiState.EXPAND)
            archives[previousIndex].isSelected = false
            archives[currentIndex].isSelected = true
            notifyItemChanged(previousIndex)
        }
        holder.onBind(archives[position])
        Log.d("asdf", "번호: $position")
    }

    override fun getItemCount(): Int = archives.size

    fun initArchives(items: List<ArchivePostingData>) {
        archives.addAll(items)
        notifyDataSetChanged()
    }
}
