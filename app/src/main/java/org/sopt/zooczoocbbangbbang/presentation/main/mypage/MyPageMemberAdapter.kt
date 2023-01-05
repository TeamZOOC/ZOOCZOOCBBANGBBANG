package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerMembersBinding

class MyPageMemberAdapter(context: Context) :
    RecyclerView.Adapter<MyPageMemberAdapter.memberViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    var memberList: List<MemberInfo> = emptyList()
    lateinit var membersBinding: ItemMypageRecyclerMembersBinding

    class memberViewHolder(
        private val binding: ItemMypageRecyclerMembersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MemberInfo) {
            binding.imgMembers.load(data.image)
            binding.tvMembers.text = data.name
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

    fun setMemberlist(memberlist: List<MemberInfo>) {
        this.memberList = memberlist.toList()
    }
}