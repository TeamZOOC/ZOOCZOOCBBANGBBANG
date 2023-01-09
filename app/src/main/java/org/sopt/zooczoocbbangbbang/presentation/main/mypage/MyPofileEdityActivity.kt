package org.sopt.zooczoocbbangbbang.presentation.main.mypage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import coil.load
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityMyPageEditProfileBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class MyPofileEdityActivity :
    BindingActivity<ActivityMyPageEditProfileBinding>(R.layout.activity_my_page_edit_profile),
    MyCustomDialogInterface {
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            binding.ivProfile.load(uri)
        }

    private val editProfileViewModel:EditProfileViewModel by viewModels()
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
            exitDialog = MyProfileExitCustomDialog(this, this)
            exitDialog.window?.setGravity(Gravity.CENTER)
            // Custom Dialog 배경 설정 (다음과 같이 진행해야 좌우 여백 없이 그려짐)
            exitDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            exitDialog.show()

        }
        binding.btnFinish.setOnClickListener{
            Log.d("aaa","버튼 클릭됨")
        }
        observe()

    }

    private fun observe(){
        editProfileViewModel.inputText.observe(this){
            Log.d("MyProfileActivity", "데이터값:::: ${editProfileViewModel.inputText.value}")
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

    private fun selectImage() {
        getContent.launch("image/*")
    }

    // // 사진을 불러오는 함수
    // private fun clickImageBtn() {
    //     val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
    //         binding.ivRecordBgimg.load(it) {
    //             transformations()
    //         }
    //     }
    //     binding.ivRecordImgbtn.setOnClickListener {
    //         launcher.launch("image/*")
    //     }
    // }
}