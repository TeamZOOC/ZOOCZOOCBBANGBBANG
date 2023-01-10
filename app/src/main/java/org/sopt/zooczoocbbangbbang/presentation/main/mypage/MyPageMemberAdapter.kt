package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerMembersBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerMembersFirstBinding

class MyPageMemberAdapter(context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    var memberList = mutableListOf<ResponseMembersDto.Data.FamilyMember>()

    class memberViewHolder(
        private val binding: ItemMypageRecyclerMembersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMembersDto.Data.FamilyMember) {
            binding.imgMembers.load(data.photo)
            binding.tvMembers.text = data.nickName
        }
    }

    class memberFirstViewHolder(
        private val binding: ItemMypageRecyclerMembersFirstBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMembersDto.Data.FamilyMember) {
            binding.imgMembers.load(data.photo)
            binding.tvMembers.text = data.nickName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MyPageMemberAdapter.MYPROFILE -> {
                Log.d("asdf", "펫 뷰홀더 생성")
                val binding = ItemMypageRecyclerMembersFirstBinding.inflate(inflater, parent, false)
                MyPageMemberAdapter.memberFirstViewHolder(binding)
            }
            else -> {
                Log.d("asdf", "추가 뷰홀더 생성")
                val binding = ItemMypageRecyclerMembersBinding.inflate(inflater, parent, false)
                MyPageMemberAdapter.memberViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyPageMemberAdapter.memberViewHolder) {
            holder.onBind(memberList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            1 -> {
                return MyPageMemberAdapter.MYPROFILE
            }
            else -> return MyPageMemberAdapter.PROFILE
        }
    }

    override fun getItemCount() = memberList.size

    fun setMemberlist(memberlist: List<ResponseMembersDto.Data.FamilyMember>) {
        memberList.addAll(memberlist)
        notifyDataSetChanged()
    }

    companion object {
        const val MYPROFILE = 0
        const val PROFILE = 1
    }
}
