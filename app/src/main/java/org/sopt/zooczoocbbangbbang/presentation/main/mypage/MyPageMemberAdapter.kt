package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.data.remote.entity.mypage.ResponseMembersDto
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerMembersBinding
import org.sopt.zooczoocbbangbbang.databinding.ItemMypageRecyclerMembersFirstBinding

class MyPageMemberAdapter(context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var memberList: List<ResponseMembersDto.Data.FamilyMember> = emptyList()

    class memberViewHolder(
        private val binding: ItemMypageRecyclerMembersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMembersDto.Data.FamilyMember) {
            if (data.photo == null) {
                binding.imgMembers.load(R.drawable.img_default_member)
            } else {
                binding.imgMembers.load(data.photo)
            }
            binding.tvMembers.text = data.nickName
        }
    }

    class memberFirstViewHolder(
        private val binding: ItemMypageRecyclerMembersFirstBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMembersDto.Data.FamilyMember) {
            if (data.photo == null) {
                binding.imgMembers.load(R.drawable.img_default_member)
            } else {
                binding.imgMembers.load(data.photo)
            }
            binding.tvMembers.text = data.nickName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MYPROFILE -> {
                Log.d("qwer", "마이프로필 뷰홀더 생성")
                val binding = ItemMypageRecyclerMembersFirstBinding.inflate(inflater, parent, false)
                memberFirstViewHolder(binding)
            }
            else -> {
                Log.d("qwer", "프로필 뷰홀더 생성")
                val binding = ItemMypageRecyclerMembersBinding.inflate(inflater, parent, false)
                memberViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is memberFirstViewHolder) {
            holder.onBind(memberList[0])
            Log.d("qwer", "마이프로필 onBindViewHolder: ${memberList[0]}")
        } else if (holder is memberViewHolder) {
            holder.onBind(memberList[position])
            Log.d("qwer", "프로필 onBindViewHolder: ${memberList[position]}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> {
                return MYPROFILE
            }
            else -> return PROFILE
        }
    }

    override fun getItemCount() = memberList.size

    fun setMemberlist(memberList: List<ResponseMembersDto.Data.FamilyMember>) {
        this.memberList = memberList.toList()
        Log.d("qwer", "memberList: $memberList")
        notifyDataSetChanged()
    }

    fun getMemebersSize(): Int {
        return memberList.size
    }

    companion object {
        const val MYPROFILE = 0
        const val PROFILE = 1
    }
}
