package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerBtnPetsAddBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerBtnPetsAddBinding.inflate
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerPetsBinding

class MyPagePetAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    var petList = mutableListOf<ResponseMembersDto.Data.Pet>()

    class PetViewHolder(
        private val binding: ItemMypageRecyclerPetsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMembersDto.Data.Pet) {
            if (data.photo == null) {
                binding.ivMypagePetImage.load(R.drawable.img_default_pet)
            } else {
                binding.ivMypagePetImage.load(data.photo)
            }
            binding.tvMypagePetName.text = data.name
        }
    }

    class AddPetViewHolder(
        private val binding: ItemMypageRecyclerBtnPetsAddBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun click(context: Context) {
            binding.btnAddPet.setOnClickListener {
                context.startActivity(Intent(context, PetRegisterActivity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PET -> {
                Log.d("asdf", "펫 뷰홀더 생성")
                val binding = ItemMypageRecyclerPetsBinding.inflate(inflater, parent, false)
                PetViewHolder(binding)
            }
            else -> {
                Log.d("asdf", "추가 뷰홀더 생성")
                val binding = inflate(inflater, parent, false)
                AddPetViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PetViewHolder) {
            holder.onBind(petList[position])
        } else if (holder is AddPetViewHolder) {
            holder.click(context)
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            petList.size -> {
                if (petList.size == 4) {
                    return PET
                }
                return ADD
            }
            else -> return PET
        }
    }

    override fun getItemCount(): Int {
        if (petList.size == 4) {
            return petList.size
        }
        return petList.size + 1
    }

    fun setPetlist(pets: List<ResponseMembersDto.Data.Pet>) {
        petList.addAll(pets)
        notifyDataSetChanged()
    }

    companion object {
        const val ADD = 0
        const val PET = 1
    }
}
