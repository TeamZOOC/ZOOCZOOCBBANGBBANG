package org.sopt.zooczoocbbangbbang.presentation.main.mypage.editprofile

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
import org.sopt.zooczoocbbangbbang.presentation.main.mypage.MyProfileExitCustomDialog
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
        getIntentData()
        clickEvents()
        observe()
        observeServerConnectFinish()
    }

    private fun clickEvents() {
        binding.ivBack.setOnClickListener {
            clickDialog()
        }
        binding.btnFinish.setOnClickListener {
            editProfileViewModel.onSubmit()
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.ivProfile.setOnClickListener {
            myCustomDialog = MyProfileEditCustomDialog(this, this)
            myCustomDialog.window?.setGravity(Gravity.BOTTOM)
            // Custom Dialog ?????? ?????? (????????? ?????? ???????????? ?????? ?????? ?????? ?????????)
            myCustomDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myCustomDialog.show()
        }
    }

    private fun getIntentData() {
        val j = intent
        val progileImg = j.getStringExtra("img")
        val nickname = j.getStringExtra("nickname")
        binding.ivProfile.load(progileImg ?: R.drawable.img_default_pet)
        binding.editText.hint = nickname
    }

    private fun clickDialog() {
        binding.ivBack.setOnClickListener {
            val dialog = CustomAlertDialog(
                this,
                "???????????? ???????????????????",
                "?????? ????????? ????????? ???????????? ?????????",
                "?????? ??????",
                "?????????",
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

    private fun observeServerConnectFinish() {
        editProfileViewModel.isProfileEditSuccess.observe(this) {
            if (it) {
                finish()
            }
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
