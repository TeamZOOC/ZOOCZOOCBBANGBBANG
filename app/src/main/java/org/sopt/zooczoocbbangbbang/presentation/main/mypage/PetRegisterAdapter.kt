package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.databinding.ItemPetRegisterRecyclerRegisteredBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemPetRegisterRecyclerToregisterBinding

class PetRegisterAdapter(context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    var petRegisterList = mutableListOf<ResponseMembersDto.Data.Pet>()

    class RegisteredViewHolder(
        private val binding: ItemPetRegisterRecyclerRegisteredBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMembersDto.Data.Pet) {
            binding.imgPet.load(data.photo)
            binding.tvPet.text = data.name
        }
    }

    class toRegisterViewHolder(
        private val binding: ItemPetRegisterRecyclerToregisterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun click(context: Context) {
            binding.imgPet.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("qwer", "onCreateViewHolder에 들어옴")
        return when (viewType) {
            REGISTERED -> {
                Log.d("qwer", "펫 뷰홀더 생성")
                val binding =
                    ItemPetRegisterRecyclerRegisteredBinding.inflate(inflater, parent, false)
                RegisteredViewHolder(binding)
            }
            else -> {
                Log.d("qwer", "펫 추가 뷰홀더 생성")
                val binding =
                    ItemPetRegisterRecyclerToregisterBinding.inflate(inflater, parent, false)
                toRegisterViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RegisteredViewHolder) {
            holder.onBind(petRegisterList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            petRegisterList.size -> {
                if (petRegisterList.size == 4) {
                    return REGISTERED
                }
                return TOREGISTER
            }
            else -> return REGISTERED
        }
    }

    override fun getItemCount(): Int {
        if (petRegisterList.size == 4) {
            return petRegisterList.size
        }
        return petRegisterList.size + 1
    }

    fun setRegisteredPetlist(pet: List<ResponseMembersDto.Data.Pet>) {
        petRegisterList.removeAll(pet)
        petRegisterList.addAll(pet)
        notifyDataSetChanged()
    }

    companion object {
        const val TOREGISTER = 0
        const val REGISTERED = 1
    }
}
