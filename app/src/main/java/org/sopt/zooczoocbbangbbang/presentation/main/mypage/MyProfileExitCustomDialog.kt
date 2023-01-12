package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import org.sopt.zooczoocbbangbbang.databinding.CustomdialogCheckQuitBinding
import org.sopt.zooczoocbbangbbang.presentation.main.mypage.editprofile.MyProfileEditCustomDialogInterface

class MyProfileExitCustomDialog(
    context: Context,
    MyCustomDialogInterface: MyProfileEditCustomDialogInterface
) : Dialog(context) {
    private var mBinding: CustomdialogCheckQuitBinding? = null
    private val binding get() = mBinding!!
    private var ExitDialogInterface: MyProfileEditCustomDialogInterface? = null

    // 인터페이스 연결
    init {
        this.ExitDialogInterface = MyCustomDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = CustomdialogCheckQuitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 커스텀 다이얼로그 외부 영역 클릭 시 꺼짐 현상 방지
        setCanceledOnTouchOutside(false)

        binding.tvRewrite.setOnClickListener {
            this.ExitDialogInterface?.rewriteBtnClicked()
        }
        binding.tvExit.setOnClickListener {
            this.ExitDialogInterface?.exitBtnClicked()
        }
    }
}
