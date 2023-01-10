package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import androidx.lifecycle.ViewModel
import org.sopt.zooczoocbbangbbang.R

class PetTestViewModel : ViewModel() {
    val exampleList = listOf<PetInfo>(
        PetInfo(
            image = R.drawable.img_mypet,
            name = "구름 엄마",
            viewtype = 1
        ),
        PetInfo(
            image = R.drawable.img_mypet,
            name = "구름 엄마",
            viewtype = 1
        ),
        PetInfo(
            image = R.drawable.img_mypet,
            name = "구름 엄마",
            viewtype = 1
        ),
        PetInfo(
            image = null,
            name = null,
            viewtype = 2
        )
    )
}
