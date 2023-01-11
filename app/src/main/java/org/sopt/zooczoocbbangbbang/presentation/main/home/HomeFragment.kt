package org.sopt.zooczoocbbangbbang.presentation.main.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentHomeBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.home.adapter.ArchiveItemDecorator
import org.sopt.zooczoocbbangbbang.presentation.main.home.adapter.ArchivePostingAdapter
import org.sopt.zooczoocbbangbbang.presentation.main.home.adapter.PetAdapter
import org.sopt.zooczoocbbangbbang.presentation.main.home.state.LayoutManagerType
import org.sopt.zooczoocbbangbbang.util.DisplayUtil.dpToPx

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var petAdapter: PetAdapter
    private lateinit var archivePostingAdapter: ArchivePostingAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPetAdapter()
        initArchiveAdapter()
        homeViewModel.getRecords()
        homeViewModel.getPets()
        clickLinearButton()
        clickGridButton()
        clickOutside()
    }

    private fun initPetAdapter() {
        petAdapter = PetAdapter()
        binding.rvHomePet.adapter = petAdapter
        removeRecyclerViewAnimator(binding.rvHomePet)
    }

    private fun initArchiveAdapter() {
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        gridLayoutManager =
            GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

        binding.rvArchivePosting.layoutManager = linearLayoutManager
        archivePostingAdapter = ArchivePostingAdapter()
        binding.rvArchivePosting.adapter = archivePostingAdapter
        binding.rvArchivePosting.addItemDecoration(ArchiveItemDecorator(requireContext(), 10, 0))
        removeRecyclerViewAnimator(binding.rvArchivePosting)
    }

    private fun removeRecyclerViewAnimator(rv: RecyclerView) {
        val animator = rv?.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    private fun clickLinearButton() {
        binding.clMainLinear.setOnClickListener {
            if (archivePostingAdapter.layoutManagerType == LayoutManagerType.LINEAR) {
                return@setOnClickListener
            }
            convertLayoutManager()
        }
    }

    private fun clickGridButton() {
        binding.clMainGrid.setOnClickListener {
            if (archivePostingAdapter.layoutManagerType == LayoutManagerType.GRID) {
                return@setOnClickListener
            }
            convertLayoutManager()
        }
    }

    private fun clickOutside() {
        binding.clHome.setOnClickListener {
            archivePostingAdapter.foldItem()
        }
    }

    private fun convertLayoutManager() {
        var position = 0
        when (archivePostingAdapter.layoutManagerType) {
            LayoutManagerType.LINEAR -> {
                showGridButton()
                if (binding.rvArchivePosting.layoutManager != null) {
                    position =
                        (binding.rvArchivePosting.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                }
                binding.rvArchivePosting.layoutManager = gridLayoutManager
                archivePostingAdapter.layoutManagerType = LayoutManagerType.GRID
                binding.rvArchivePosting.adapter = archivePostingAdapter
                setArchivePostingPadding(listOf(30, 0, 20, 72))
                binding.rvArchivePosting.removeItemDecorationAt(0)
                binding.rvArchivePosting
                    .addItemDecoration(ArchiveItemDecorator(requireContext(), 10, 10))
                convertConstraint(LayoutManagerType.GRID)
            }
            LayoutManagerType.GRID -> {
                showLinearButton()
                if (binding.rvArchivePosting.layoutManager != null) {
                    position =
                        (binding.rvArchivePosting.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
                }
                binding.rvArchivePosting.layoutManager = linearLayoutManager
                archivePostingAdapter.layoutManagerType = LayoutManagerType.LINEAR
                binding.rvArchivePosting.adapter = archivePostingAdapter
                setArchivePostingPadding(listOf(30, 0, 0, 0))
                binding.rvArchivePosting.removeItemDecorationAt(0)
                binding.rvArchivePosting
                    .addItemDecoration(ArchiveItemDecorator(requireContext(), 10, 0))
                convertConstraint(LayoutManagerType.LINEAR)
            }
        }
        binding.rvArchivePosting.scrollToPosition(position)
    }

    private fun setArchivePostingPadding(paddingValues: List<Int>) {
        val temp = paddingValues.map { dpToPx(requireContext(), it) }
        binding.rvArchivePosting.setPadding(temp[0], temp[1], temp[2], temp[3])
    }

    private fun convertConstraint(
        typeToConvert: LayoutManagerType
    ) {
        when (typeToConvert) {
            LayoutManagerType.LINEAR -> binding.rvArchivePosting.layoutParams.height = WRAP_CONTENT
            LayoutManagerType.GRID -> binding.rvArchivePosting.layoutParams.height = 0
        }
        val constraintLayout = binding.clHome
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        when (typeToConvert) {
            LayoutManagerType.LINEAR ->
                constraintSet.clear(binding.rvArchivePosting.id, ConstraintSet.BOTTOM)
            LayoutManagerType.GRID ->
                constraintSet.connect(
                    binding.rvArchivePosting.id,
                    ConstraintSet.BOTTOM,
                    binding.clHome.id,
                    ConstraintSet.BOTTOM,
                    0
                )
        }
        constraintSet.applyTo(constraintLayout)
        binding.rvArchivePosting.requestLayout()
    }

    private fun showLinearButton() {
        binding.ivMainLinearSelected.visibility = View.VISIBLE
        binding.ivMainLinearUnselected.visibility = View.INVISIBLE
        binding.ivMainGridUnselected.visibility = View.VISIBLE
        binding.ivMainGridSelected.visibility = View.INVISIBLE
    }

    private fun showGridButton() {
        binding.ivMainLinearSelected.visibility = View.INVISIBLE
        binding.ivMainLinearUnselected.visibility = View.VISIBLE
        binding.ivMainGridUnselected.visibility = View.INVISIBLE
        binding.ivMainGridSelected.visibility = View.VISIBLE
    }
}
