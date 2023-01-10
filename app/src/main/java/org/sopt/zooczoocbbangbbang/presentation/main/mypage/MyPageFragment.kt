package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMyPageBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment

class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private lateinit var myPageMemberAdapter: MyPageMemberAdapter
    private lateinit var myPagePetAdapter: MyPagePetAdapter
    private val memberviewModel: MemberViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        fetchUserProfile()
        addMember()
        addPet()
        binding.tvBtnEdit.setOnClickListener {
            requireActivity().startActivity(Intent(activity, MyPofileEdityActivity::class.java))
        }
        binding.tvBtnAppInfo.setOnClickListener {
            requireActivity().startActivity(Intent(activity, MyprofileAppInfoActivity::class.java))
        }
        binding.tvBtnSetAlarm.setOnClickListener {
            requireActivity().startActivity(Intent(activity, MyprofileSetAlarmActivity::class.java))
        }
    }

    private fun initAdapter() {
        myPageMemberAdapter = MyPageMemberAdapter(requireContext())
        binding.rvMembers.adapter = myPageMemberAdapter

        myPagePetAdapter = MyPagePetAdapter(requireContext())
        binding.rvPets.adapter = myPagePetAdapter
    }

    fun fetchUserProfile() {
        memberviewModel.userData.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.ivMyprofile.load(user.imgMember)
                binding.tvNickname.text = user.memberName
            }
        }
    }

    private fun addMember() {
        memberviewModel.memberData.observe(viewLifecycleOwner) { member ->
            if (member != null) {
                myPageMemberAdapter.setMemberlist(member)
                binding.tvMemberNum.text = myPageMemberAdapter.memberList?.size.toString()
            }
        }
//         MyPageMemberAdapter.setMemberlist(memberviewModel.exampleList)
//         binding.tvMemberNum.text= MyPageMemberAdapter.memberList.size.toString()
    }

    private fun addPet() {
        memberviewModel.petData.observe(viewLifecycleOwner) { pet ->
            if (pet != null) {
                myPagePetAdapter.setPetlist(pet)
                binding.tvCountPet.text = myPagePetAdapter.petList?.size.toString()
            }
        }
        // MyPagePetAdapter.setPetlist(petviewModel.exampleList)
        // binding.tvCountPet.text = (MyPagePetAdapter.petList.size - 1).toString()
    }
}
