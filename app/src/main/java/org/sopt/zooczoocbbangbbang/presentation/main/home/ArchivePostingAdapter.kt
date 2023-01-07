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
                Log.d("asdf", "리니어 크리에이트")
                ArchivePostingLinearViewHolder(binding)
            }
            LayoutManagerType.GRID -> {
                val binding = ItemGridArchivePostingBinding.inflate(layoutInflater, parent, false)
                Log.d("asdf", "그리드 크리에이트")
                ArchivePostingGridViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ArchivePostingViewHolder, position: Int) {
        if (holder is ArchivePostingLinearViewHolder) {
            bindLinearViewHolder(holder, position)
        }
        holder.onBind(archives[position])
        Log.d("asdf", "onBindViewHolder position : $position / isSelected ${archives[position].isSelected} / date ${archives[position].date}")
    }

    private fun bindLinearViewHolder(holder: ArchivePostingLinearViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            previousIndex = currentIndex
            currentIndex = position
            holder.updateUi(FoldableUiState.EXPAND)
            archives[previousIndex].isSelected = false
            archives[currentIndex].isSelected = true
            notifyItemChanged(previousIndex)
        }
    }

    override fun getItemCount(): Int = archives.size

    fun initArchives(items: List<ArchivePostingData>) {
        archives.addAll(items)
        notifyDataSetChanged()
    }
}
