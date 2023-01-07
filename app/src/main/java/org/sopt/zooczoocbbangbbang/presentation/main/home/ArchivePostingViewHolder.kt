package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.databinding.ItemGridArchivePostingBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemLinearArchivePostingBinding
import org.sopt.zooczoocbbangbbang.domain.ArchivePostingData
import org.sopt.zooczoocbbangbbang.util.GlideExt

abstract class ArchivePostingViewHolder(private val binding: View) :
    RecyclerView.ViewHolder(binding) {
    abstract fun onBind(data: ArchivePostingData)
}

class ArchivePostingLinearViewHolder(private val binding: ItemLinearArchivePostingBinding) :
    ArchivePostingViewHolder(binding.root) {

    override fun onBind(data: ArchivePostingData) {
        GlideExt.loadIntImage(data.petImage, binding.ivArchivePetImage)
        GlideExt.loadIntImage(data.editorImage, binding.ivArchiveUploaderProfile)
        binding.data = data
        if (data.isSelected) {
            expandItem()
            Log.d("asdf", "expand / end state: ${binding.mlArchivePosting.endState}")
        } else {
            foldItem()
            Log.d("asdf", "fold / start state: ${binding.mlArchivePosting.startState}")
        }
    }

    fun updateUi(foldableUiState: FoldableUiState) {
        when (foldableUiState) {
            FoldableUiState.EXPAND -> expandItem()
            FoldableUiState.FOLD -> foldItem()
        }
    }

    private fun expandItem() {
        binding.mlArchivePosting.transitionToEnd()
        Log.d("asdf", "확장됨")
    }

    private fun foldItem() {
        binding.mlArchivePosting.transitionToStart()
        Log.d("asdf", "접기")
    }
}

class ArchivePostingGridViewHolder(private val binding: ItemGridArchivePostingBinding) :
    ArchivePostingViewHolder(binding.root) {
    override fun onBind(data: ArchivePostingData) {
        GlideExt.loadIntImage(data.petImage, binding.ivArchiveGridPetImage)
    }
}
