package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.databinding.ItemGridArchivePostingBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemLinearArchivePostingBinding
import org.sopt.zooczoocbbangbbang.domain.ArchivePostingData

class ArchivePostingAdapter :
    RecyclerView.Adapter<ArchivePostingViewHolder>() {
    private val archives = mutableListOf<ArchivePostingData>()
    private var currentIndex: Int = 0
    private var previousIndex: Int = -1
    var layoutManagerType = LayoutManagerType.LINEAR

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchivePostingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (layoutManagerType) {
            LayoutManagerType.LINEAR -> {
                val binding = ItemLinearArchivePostingBinding.inflate(layoutInflater, parent, false)
                ArchivePostingLinearViewHolder(binding)
            }
            LayoutManagerType.GRID -> {
                val binding = ItemGridArchivePostingBinding.inflate(layoutInflater, parent, false)
                ArchivePostingGridViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ArchivePostingViewHolder, position: Int) {
        if (holder is ArchivePostingLinearViewHolder) {
            holder.onBind(archives[position]) { clickItem(position) }
        } else {
            holder.onBind(archives[position]) {}
        }
        holder.onBind(archives[position])
        Log.d("asdf", "onBindViewHolder position : $position / isSelected ${archives[position].isSelected} / date ${archives[position].date}")
    }

    private fun clickItem(position: Int) {
        previousIndex = currentIndex
        currentIndex = position
        archives[previousIndex].isSelected = false
        archives[currentIndex].isSelected = true
        notifyItemChanged(currentIndex)
        notifyItemChanged(previousIndex)
    }

    override fun getItemCount(): Int = archives.size

    fun initArchives(items: List<ArchivePostingData>) {
        archives.addAll(items)
        notifyDataSetChanged()
    }
}
