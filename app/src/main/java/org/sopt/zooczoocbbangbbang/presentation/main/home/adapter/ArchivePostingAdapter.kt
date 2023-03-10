package org.sopt.zooczoocbbangbbang.presentation.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.databinding.ItemGridArchivePostingBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemLinearArchivePostingBinding
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.ArchivePostingData
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.RecordTransportData
import org.sopt.zooczoocbbangbbang.presentation.main.home.state.LayoutManagerType

class ArchivePostingAdapter(private val clickExpandedItem: (views: Map<String, View>, RecordTransportData) -> Unit) :
    RecyclerView.Adapter<ArchivePostingViewHolder>() {
    private var archives: List<ArchivePostingData> = emptyList()
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
            holder.onBind(archives[position], { clickItem(position) }) { options, bundle ->
                clickExpandedItem(options, bundle)
            }
        } else {
            holder.onBind(archives[position], {}) { options, bundle ->
                clickExpandedItem(options, bundle)
            }
            holder.setIsRecyclable(false)
        }
    }

    private fun clickItem(position: Int) {
        previousIndex = currentIndex
        currentIndex = position
        archives[previousIndex].isSelected = false
        archives[currentIndex].isSelected = true
        notifyItemChanged(currentIndex)
        notifyItemChanged(previousIndex)
    }

    fun initArchives(items: List<ArchivePostingData>) {
        archives = items.toList()
        notifyDataSetChanged()
    }

    fun foldItem() {
        archives[currentIndex].isSelected = false
        notifyItemChanged(currentIndex)
        currentIndex = 0
        previousIndex = -1
    }

    fun clearItemPosition() {
        currentIndex = 0
        previousIndex = -1
    }

    override fun getItemCount(): Int = archives.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
