package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerMembersBinding

class MyPageMemberAdapter(context: Context) :
    RecyclerView.Adapter<MyPageMemberAdapter.memberViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    var memberList: List<ResponseMembersDto.Data.FamilyMember> = emptyList()
    lateinit var membersBinding: ItemMypageRecyclerMembersBinding

    class memberViewHolder(
        private val binding: ItemMypageRecyclerMembersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMembersDto.Data.FamilyMember) {
            binding.imgMembers.load(data.photo)
            binding.tvMembers.text = data.nickName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): memberViewHolder {
        membersBinding = ItemMypageRecyclerMembersBinding.inflate(inflater, parent, false)
        return memberViewHolder(membersBinding)
    }

    override fun onBindViewHolder(holder: memberViewHolder, position: Int) {
        holder.onBind(memberList[position])
    }

    override fun getItemCount() = memberList.size

    fun setMemberlist(memberlist: List<ResponseMembersDto.Data.FamilyMember>) {
        this.memberList = memberlist.toList()
        notifyItemInserted(memberList.lastIndex)
    }
}
