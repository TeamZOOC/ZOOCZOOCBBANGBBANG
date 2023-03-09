package org.sopt.zooczoocbbangbbang.presentation.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ItemPetBinding
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.PetData
import org.sopt.zooczoocbbangbbang.presentation.main.home.state.FoldableUiState

class PetAdapter(private val clickItem: (Int) -> Unit) :
    RecyclerView.Adapter<PetAdapter.PetViewHolder>() {
    private var pets: List<PetData> = emptyList()
    private var currentIndex = 0
    private var previousIndex = -1

    class PetViewHolder(private val binding: ItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: PetData, clickItem: () -> Unit) {
            binding.tvPetItemName.text = data.name
            binding.ivHomePetProfile.load(data.photo ?: R.drawable.ic_default_image)
            when (data.isSelected) {
                true -> updateUi(FoldableUiState.EXPAND)
                false -> updateUi(FoldableUiState.FOLD)
            }
            itemView.setOnClickListener {
                clickItem()
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
        pets[currentIndex].isSelected = true
        holder.onBind(pets[position]) { clickItem(holder, position) }
        holder.setIsRecyclable(false)
    }

    private fun clickItem(holder: PetViewHolder, position: Int) {
        previousIndex = currentIndex
        currentIndex = position
        holder.updateUi(FoldableUiState.EXPAND)
        pets[previousIndex].isSelected = false
        pets[currentIndex].isSelected = true
        notifyItemChanged(previousIndex)
        clickItem(pets[position].id)
    }

    override fun getItemCount(): Int = pets.size

    fun initPets(items: List<PetData>) {
        pets = items
        notifyDataSetChanged()
    }
}
