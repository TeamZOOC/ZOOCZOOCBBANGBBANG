package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.SimpleItemAnimator
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentHomeBinding
import org.sopt.zooczoocbbangbbang.domain.PetData
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var adapter: PetAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initList()
    }

    private fun initAdapter() {
        adapter = PetAdapter()
        binding.rvHomePet.adapter = adapter
        val animator = binding.rvHomePet?.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    private fun initList() {
        val pets = listOf(
            PetData(R.drawable.ym1, "용민", true),
            PetData(R.drawable.ym2, "진수", false),
            PetData(R.drawable.ym3, "지영", false),
            PetData(R.drawable.ym4, "지은", false)
        )
        adapter.initPets(pets)
    }
}
