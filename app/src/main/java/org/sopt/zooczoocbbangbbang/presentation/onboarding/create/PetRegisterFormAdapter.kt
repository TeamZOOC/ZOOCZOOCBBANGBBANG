package org.sopt.zooczoocbbangbbang.presentation.onboarding.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ItemOnboardingPetRegisterBinding
import org.sopt.zooczoocbbangbbang.presentation.onboarding.create.model.PetUiModel

class PetRegisterFormAdapter(
    val onCancelListener: (Int) -> Unit,
    val onSelectImageListener: () -> Unit
) : ListAdapter<PetUiModel, PetRegisterFormAdapter.ViewHolder>(
    diffUtil
),
    PetRegisterFormListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemOnboardingPetRegisterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_onboarding_pet_register,
            parent,
            false
        )
        return ViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), position)
    }

    override fun onSelectImage() {
        onSelectImageListener()
    }

    override fun onCancel(position: Int) {
        onCancelListener(position)
    }

    inner class ViewHolder(
        private val binding: ItemOnboardingPetRegisterBinding,
        private val petRegisterFormListener: PetRegisterFormListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PetUiModel, position: Int) {
            binding.apply {
                this.position = position
                data = item
                listener = petRegisterFormListener
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PetUiModel>() {
            override fun areItemsTheSame(oldItem: PetUiModel, newItem: PetUiModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PetUiModel, newItem: PetUiModel): Boolean {
                return oldItem.name == newItem.name && oldItem.uriString == newItem.uriString
            }
        }
    }
}
