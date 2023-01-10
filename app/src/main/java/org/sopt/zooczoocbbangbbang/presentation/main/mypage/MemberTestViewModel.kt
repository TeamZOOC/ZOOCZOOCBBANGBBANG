package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.R

class MemberTestViewModel : ViewModel() {
    val exampleList = listOf<MemberInfo>(
        MemberInfo(
            image = R.drawable.mypage_member,
            name = "구름 엄마"

        ),
        MemberInfo(
            image = R.drawable.mypage_member,
            name = "구름 아빠"

        ),
        MemberInfo(
            image = R.drawable.mypage_member,
            name = "구름 언니"

        ),
        MemberInfo(
            image = R.drawable.mypage_member,
            name = "구름 오빠"

        ),
        MemberInfo(
            image = R.drawable.mypage_member,
            name = "구름 엄마"

        ),
        MemberInfo(
            image = R.drawable.mypage_member,
            name = "구름 아빠"

        ),
        MemberInfo(
            image = R.drawable.mypage_member,
            name = "구름 언니"

        )
    )
}
