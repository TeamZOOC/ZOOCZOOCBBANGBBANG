package org.sopt.zooczoocbbangbbang.presentation.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.zooczoocbbangbbang.data.remote.api.ServiceFactory
import org.sopt.zooczoocbbangbbang.data.remote.entity.auth.ResponseSignUpDto
import retrofit2.HttpException
import retrofit2.await

class AuthViewModel : ViewModel() {
    val signUpResult = MutableLiveData<ResponseSignUpDto>()
    private val zoocService = ServiceFactory.zoocService

    fun postSignUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                zoocService.postToken().await()
            }.onSuccess {
                signUpResult.value = it
            }.onFailure {
                if (it is HttpException) {
                    Log.e("SignUpActivity", "회원 가입 서버 통신 onResponse but not successful")
                } else {
                    Log.e("SignUpActivity", "회원 가입 서버 통신 onFailure")
                }
            }
        }
    }
}
