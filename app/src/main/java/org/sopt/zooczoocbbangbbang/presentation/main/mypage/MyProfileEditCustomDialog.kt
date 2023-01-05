package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import org.sopt.zooczoocbbangbbang.R

class MyProfileEditCustomDialog(context: Context,
    MyCustomDialogInterface: MyCustomDialogInterface)
    : Dialog(context) {
    private var myCustomDialogInterface: MyCustomDialogInterface? = null

    // 인터페이스 연결
    init {
        this.myCustomDialogInterface = MyCustomDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.custumdialog_edit_photo)

        // 배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }
}