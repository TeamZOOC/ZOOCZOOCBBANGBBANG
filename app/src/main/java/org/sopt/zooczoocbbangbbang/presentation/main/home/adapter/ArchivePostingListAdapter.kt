package org.sopt.zooczoocbbangbbang.presentation.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.sopt.zooczoocbbangbbang.databinding.ItemGridArchivePostingBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemLinearArchivePostingBinding
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.ArchivePostingData
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.RecordTransportData
import org.sopt.zooczoocbbangbbang.presentation.main.home.state.LayoutManagerType

class ArchivePostingListAdapter(
    private val clickItem: (Int) -> Unit,
    private val clickExpandedItem: (views: Map<String, View>, RecordTransportData) -> Unit
) : ListAdapter<ArchivePostingData, ArchivePostingViewHolder>(ArchivePostingComparator()) {
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
            holder.onBind(getItem(position), { clickItem(position) }) { options, bundle ->
                clickExpandedItem(options, bundle)
            }
        } else {
            holder.onBind(getItem(position), {}) { options, bundle ->
                clickExpandedItem(options, bundle)
            }
        }
    }

    class ArchivePostingComparator : DiffUtil.ItemCallback<ArchivePostingData>() {
        override fun areItemsTheSame(
            oldItem: ArchivePostingData,
            newItem: ArchivePostingData
        ): Boolean {
            val isIdSame = (oldItem.record.id == newItem.record.id)
            val isSelectedSame = (oldItem.isSelected == newItem.isSelected)
            Log.d("qwer", "isItemSame - oldItem: ${oldItem.isSelected} / newItem: ${newItem.isSelected}")
            return (isIdSame and isSelectedSame)
        }

        override fun areContentsTheSame(
            oldItem: ArchivePostingData,
            newItem: ArchivePostingData
        ): Boolean {
            return oldItem == newItem
        }
    }
}
