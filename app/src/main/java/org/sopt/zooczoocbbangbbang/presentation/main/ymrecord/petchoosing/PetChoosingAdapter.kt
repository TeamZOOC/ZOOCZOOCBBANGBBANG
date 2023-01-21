package org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.petchoosing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.databinding.ItemPetChoosingBinding
import org.sopt.zooczoocbbangbbang.presentation.main.home.data.PetData

class PetChoosingAdapter(private val clickItem: (Int) -> Unit) :
    RecyclerView.Adapter<PetChoosingAdapter.PetChoosingViewHolder>() {
    private var pets: List<PetData> = emptyList()

    class PetChoosingViewHolder(private val binding: ItemPetChoosingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PetData) {
            binding.data = data
            binding.ivPetChoosingProfile.load(data.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetChoosingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPetChoosingBinding.inflate(layoutInflater, parent, false)
        return PetChoosingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetChoosingViewHolder, position: Int) {
        holder.itemView.setOnClickListener { clickItem(position) }
        holder.onBind(pets[position])
    }

    override fun getItemCount() = pets.size

    fun setPets(items: List<PetData>) {
        pets = items.toList()
        notifyDataSetChanged()
    }

    fun getSelectedPetsId(): List<Int> {
        return pets.filter { it.isSelected }.map { it.id }
    }
}
