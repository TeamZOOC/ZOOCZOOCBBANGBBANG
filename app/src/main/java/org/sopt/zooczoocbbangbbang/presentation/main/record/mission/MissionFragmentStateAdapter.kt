package org.sopt.zooczoocbbangbbang.presentation.main.record

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MissionFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    var fragments: List<Fragment> = emptyList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragments: List<Fragment>) {
        this.fragments = fragments.toList()
        notifyItemInserted(fragments.size - 1)
    }

    /*
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
     */
}
