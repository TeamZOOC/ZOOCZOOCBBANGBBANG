package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.databinding.ItemPetBinding
import org.sopt.zooczoocbbangbbang.domain.PetData

class PetAdapter : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {
    private val pets = mutableListOf<PetData>()
    private var currentIndex = 0
    private var previousIndex = -1

    class PetViewHolder(private val binding: ItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: PetData) {
            binding.tvPetItemName.text = data.name
            binding.ivHomePetProfile.load(data.image)
            when (data.isSelected) {
                true -> updateUi(FoldableUiState.EXPAND)
                false -> updateUi(FoldableUiState.FOLD)
            }
        }

        fun updateUi(petUiState: FoldableUiState) {
            when (petUiState) {
                FoldableUiState.FOLD -> foldItem()
                FoldableUiState.EXPAND -> expandItem()
            }
        }

        private fun foldItem() {
            binding.mlPetItem.transitionToStart()
        }

        private fun expandItem() {
            binding.mlPetItem.transitionToEnd()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPetBinding.inflate(layoutInflater, parent, false)
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            previousIndex = currentIndex
            currentIndex = position
            holder.updateUi(FoldableUiState.EXPAND)
            pets[previousIndex].isSelected = false
            pets[currentIndex].isSelected = true
            notifyItemChanged(previousIndex)
        }
        holder.onBind(pets[position])
    }

    override fun getItemCount(): Int = pets.size

    fun initPets(items: List<PetData>) {
        pets.addAll(items)
        notifyDataSetChanged()
    }
}
