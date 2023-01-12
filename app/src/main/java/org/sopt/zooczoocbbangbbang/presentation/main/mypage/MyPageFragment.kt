package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.FragmentMyPageBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingFragment
import org.sopt.zooczoocbbangbbang.presentation.main.mypage.editprofile.MyPofileEdityActivity

class MyPageFragment :
    BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page),
    MyPageCustomDialogInterface {
    private lateinit var myPageMemberAdapter: MyPageMemberAdapter
    private lateinit var myPagePetAdapter: MyPagePetAdapter
    private val memberviewModel: MemberViewModel by viewModels()

    lateinit var secessionDialog: MyPageSecessionCustomDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBtnInvite.setOnClickListener {
            Toast.makeText(requireContext(), "링크 복사 완료", Toast.LENGTH_SHORT)
                .show()
        }
        binding.tvBtnEdit.setOnClickListener {
            var i = Intent(activity, MyPofileEdityActivity::class.java)
            i.putExtra("img", memberviewModel.userData.value?.imgMember)
            i.putExtra("nickname", memberviewModel.userData.value?.memberName)
            requireActivity().startActivity(i)
        }
        binding.tvBtnAppInfo.setOnClickListener {
            requireActivity().startActivity(Intent(activity, MyprofileAppInfoActivity::class.java))
        }
        binding.tvBtnSetAlarm.setOnClickListener {
            requireActivity().startActivity(Intent(activity, MyprofileSetAlarmActivity::class.java))
        }
        binding.tvBtnSecession.setOnClickListener {
            secessionDialog = MyPageSecessionCustomDialog(requireContext(), this)
            secessionDialog.window?.setGravity(Gravity.CENTER)
            // Custom Dialog 배경 설정 (다음과 같이 진행해야 좌우 여백 없이 그려짐)
            secessionDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            secessionDialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        getMypageData()
        fetchUserProfile()
        initAdapter()
        addMember()
        addPet()
    }

    private fun initAdapter() {
        myPageMemberAdapter = MyPageMemberAdapter(requireContext())
        binding.rvMembers.adapter = myPageMemberAdapter

        myPagePetAdapter = MyPagePetAdapter(requireContext())
        binding.rvPets.adapter = myPagePetAdapter
    }

    private fun getMypageData() {
        memberviewModel.fetchMyPageData()
    }

    private fun fetchUserProfile() {
        memberviewModel.userData.observe(viewLifecycleOwner) { user ->
            Log.d("aaa", "$user")
            if (user.imgMember == null) {
                Log.d("aaa", "이미지 널 좋아해")
                binding.cvProfile.load(R.drawable.img_default_pet)
                binding.tvNickname.text = user.memberName
            } else {
                Log.d("aaa", "릴리 릴리 릴리")
                binding.cvProfile.load(user.imgMember)
                binding.tvNickname.text = user.memberName
            }
        }
    }

    private fun addMember() {
        memberviewModel.memberData.observe(viewLifecycleOwner) { member ->
            if (member != null) {
                myPageMemberAdapter.setMemberlist(member)
                binding.tvMemberNum.text = myPageMemberAdapter.getMemebersSize().toString()
            }
        }
    }

    private fun addPet() {
        memberviewModel.petData.observe(viewLifecycleOwner) { pet ->
            if (pet != null) {
                myPagePetAdapter.setPetlist(pet)
                binding.tvCountPet.text = myPagePetAdapter.petList?.size.toString()
            }
        }
    }

    override fun yesSecessionBtnClicked() {
        secessionDialog.dismiss()
    }

    override fun noSecessionBtnClicked() {
        secessionDialog.dismiss()
    }
}
