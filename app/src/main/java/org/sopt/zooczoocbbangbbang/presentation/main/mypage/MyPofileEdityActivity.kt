package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityMyPageEditProfileBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.util.ContentUriRequestBody
import org.sopt.zooczoocbbangbbang.util.CustomAlertDialog

class MyPofileEdityActivity :
    BindingActivity<ActivityMyPageEditProfileBinding>(R.layout.activity_my_page_edit_profile),
    MyProfileEditCustomDialogInterface {
    private val editProfileViewModel: EditProfileViewModel by viewModels()
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            binding.ivProfile.load(uri)
            editProfileViewModel.file.value = uri?.let { ContentUriRequestBody(this, it) }
        }
    lateinit var myCustomDialog: MyProfileEditCustomDialog
    lateinit var exitDialog: MyProfileExitCustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = editProfileViewModel
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.ivCamera.setOnClickListener {
            myCustomDialog = MyProfileEditCustomDialog(this, this)
            myCustomDialog.window?.setGravity(Gravity.BOTTOM)
            // Custom Dialog 배경 설정 (다음과 같이 진행해야 좌우 여백 없이 그려짐)
            myCustomDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myCustomDialog.show()
        }
        binding.ivBack.setOnClickListener {
            clickDialog()
        }
        binding.btnFinish.setOnClickListener {
            editProfileViewModel.onSubmit()
            finish()
        }
        // val j = intent
        // val progileImg = j.getStringExtra("img")
        // val nickname = j.getStringExtra("nickname")
        // binding.ivProfile.load(progileImg)
        // binding.editText.hint = nickname
        observe()
    }

    private fun clickDialog() {
        binding.ivBack.setOnClickListener {
            val dialog = CustomAlertDialog(
                this,
                "페이지를 나가시겠어요?",
                "지금 떠나면 내용이 저장되지 않아요",
                "이어 쓰기",
                "나가기",
                { },
                { finish() }
            )
            dialog.showDialog()
        }
    }

    private fun observe() {
        editProfileViewModel.inputText.observe(this) {
            editProfileViewModel.countText()
        }
    }

    override fun changePhotoBtnClicked() {
        selectImage()
        myCustomDialog.dismiss()
    }

    override fun deletePhotoBtnClicked() {
        binding.ivProfile.setImageResource(R.drawable.ic_basic_profile)
        myCustomDialog.dismiss()
    }

    override fun cancelBtnClicked() {
        myCustomDialog.dismiss()
    }

    override fun rewriteBtnClicked() {
        exitDialog.dismiss()
    }

    override fun exitBtnClicked() {
        finish()
    }

    fun selectImage() {
        getContent.launch("image/*")
    }
}
