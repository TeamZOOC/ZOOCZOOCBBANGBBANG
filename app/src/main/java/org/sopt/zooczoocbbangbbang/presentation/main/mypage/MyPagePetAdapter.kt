package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerBtnPetsAddBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerBtnPetsAddBinding.inflate
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerPetsBinding

class MyPagePetAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    var petList: List<PetInfo> = emptyList()

    class petViewHolder(
        private val binding: ItemMypageRecyclerPetsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PetInfo) {
            binding.ivMypagePetImage.setImageResource(data.image!!)
            binding.tvMypagePetName.text = data.name
        }
    }

    override fun getItemViewType(position: Int): Int {
        return petList[position].viewtype
    }

    class addPetViewHolder(
        private val binding: ItemMypageRecyclerBtnPetsAddBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val binding = ItemMypageRecyclerPetsBinding.inflate(inflater, parent, false)
                petViewHolder(binding)
            }
            else -> {
                val binding = inflate(inflater, parent, false)
                addPetViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (petList[position].viewtype) {
            1 -> {
                holder as petViewHolder
                holder.onBind(petList[position])
            }
            else -> {
                holder as addPetViewHolder
            }
        }
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    fun setPetlist(petlist: List<PetInfo>) {
        this.petList = petlist
    }
}
