package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMyPageBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private lateinit var MyPageMemberAdapter: MyPageMemberAdapter
    private lateinit var MyPagePetAdapter: MyPagePetAdapter
    private val memberviewModel: MemberTestViewModel by viewModels()
    private val petviewModel: PetTestViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        addMember()
        addPet()
        binding.tvBtnEdit.setOnClickListener {
            requireActivity().startActivity(Intent(activity, MyPofileEdityActivity::class.java))
        }
    }

    private fun initAdapter() {
        MyPageMemberAdapter = MyPageMemberAdapter(requireContext())
        binding.rvMembers.adapter = MyPageMemberAdapter

        MyPagePetAdapter = MyPagePetAdapter(requireContext())
        binding.rvPets.adapter = MyPagePetAdapter
    }

    private fun addMember() {
//        viewModel.memberData.observe(viewLifecycleOwner) { member ->
//            if (member != null)
//                MyPageMemberAdapter.setMemberlist(member)
//                MyPageMemberAdapter.notifyItemInserted(MyPageMemberAdapter.memberList.lastIndex)
//                binding.tvMemberNum.text= MyPageMemberAdapter.memberList.size.toString()
//        }
        MyPageMemberAdapter.setMemberlist(memberviewModel.exampleList)
        binding.tvMemberNum.text = MyPageMemberAdapter.memberList.size.toString()
    }

    private fun addPet() {
        MyPagePetAdapter.setPetlist(petviewModel.exampleList)
        binding.tvCountPet.text = (MyPagePetAdapter.petList.size - 1).toString()
    }
}
