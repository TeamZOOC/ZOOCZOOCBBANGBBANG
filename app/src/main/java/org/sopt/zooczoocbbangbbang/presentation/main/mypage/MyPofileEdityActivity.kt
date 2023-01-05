package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityMyPageEditProfileBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class MyPofileEdityActivity: BindingActivity<ActivityMyPageEditProfileBinding>(R.layout.activity_my_page_edit_profile), MyCustomDialogInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivBack.setOnClickListener{
            finish()
        }
        binding.ivCamera.setOnClickListener{
            val myCustomDialog = MyProfileEditCustomDialog(this,this)
            myCustomDialog.window?.setGravity(Gravity.BOTTOM)
            // Custom Dialog 배경 설정 (다음과 같이 진행해야 좌우 여백 없이 그려짐)
            myCustomDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myCustomDialog.show()

        }
    }

    override fun changePhotoBtnClicked() {
        TODO("Not yet implemented")
    }

    override fun deletePhotoBtnClicked() {
        TODO("Not yet implemented")
    }

    override fun cancelBtnClicked() {
        TODO("Not yet implemented")
    }
}