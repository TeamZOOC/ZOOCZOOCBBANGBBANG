package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Pet
import org.sopt.zooczoocbbangbbang.databinding.ItemPetRegisterRecyclerRegisteredBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemPetRegisterRecyclerToregisterBinding

class PetRegisterAdapter(private val context: Context, private val clickItem: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    var petRegisterList = mutableListOf<Pet>()

    class RegisteredViewHolder(
        private val binding: ItemPetRegisterRecyclerRegisteredBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Pet) {
            binding.imgPet.load(data.photo ?: R.drawable.ic_default_image)
            binding.tvPet.text = data.name
        }
    }

    class ToRegisterViewHolder(
        private val binding: ItemPetRegisterRecyclerToregisterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(clickItem: () -> Unit) {
            itemView.setOnClickListener {
                clickItem()
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
                ToRegisterViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RegisteredViewHolder) {
            holder.onBind(petRegisterList[position])
        }
        if (holder is ToRegisterViewHolder) {
            holder.onBind { clickItem }
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

    fun setRegisteredPetlist(pet: List<Pet>) {
        petRegisterList.removeAll(pet)
        petRegisterList.addAll(pet)
        notifyDataSetChanged()
    }

    fun setLastItemImage(uri: Uri?) {
    }

    companion object {
        const val TOREGISTER = 0
        const val REGISTERED = 1
    }
}
