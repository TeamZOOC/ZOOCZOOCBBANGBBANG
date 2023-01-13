package org.sopt.zooczoocbbangbbang.presentation.onboarding.create

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ItemOnboardingPetRegisterBinding
import org.sopt.zooczoocbbangbbang.presentation.onboarding.create.model.PetUiModel

class PetRegisterFormAdapter(
    val onCancelListener: (Int) -> Unit,
    val onSelectImageListener: (Int) -> Unit
) : RecyclerView.Adapter<PetRegisterFormAdapter.ViewHolder>(), PetRegisterFormListener {

    private val petRegisterForms = mutableListOf<PetUiModel>(PetUiModel())

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
        holder.onBind(petRegisterForms[position], position)
    }

    override fun getItemCount(): Int = petRegisterForms.size

    fun addNewForm() {
        if (isMaxContent()) return
        petRegisterForms.add(PetUiModel())
        this.notifyItemInserted(petRegisterForms.size - 1)
    }

    fun deleteForm(position: Int) {
        if (isMinContent()) return
        petRegisterForms.removeAt(position)
        this.notifyItemRemoved(position)
    }

    fun editItemImage(position: Int, uriString: String) {
        petRegisterForms[position].uriString = uriString
        this.notifyItemChanged(position)
    }

    fun getAllForms(): List<PetUiModel> {
        return petRegisterForms.toList()
    }

    fun isMaxContent(): Boolean {
        return petRegisterForms.size >= 4
    }

    fun isMinContent(): Boolean {
        return petRegisterForms.size <= 1
    }

    override fun onSelectImage(position: Int) {
        onSelectImageListener(position)
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
}
