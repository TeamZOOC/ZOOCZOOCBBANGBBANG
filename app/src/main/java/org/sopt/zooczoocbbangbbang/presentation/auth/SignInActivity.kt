package org.sopt.zooczoocbbangbbang.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.data.local.ZoocStorage
import org.sopt.zooczoocbbangbbang.databinding.ActivitySignInBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.MainActivity

const val KAKAO_LOGIN = "kakaoLogin"

class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivKakaoLogin.setOnClickListener {
            kakaoLogin()
            storeToken()
        }
    }

    private fun kakaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.d(KAKAO_LOGIN, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.d(KAKAO_LOGIN, "카카오계정으로 로그인 성공 - 액세스 토큰: ${token.accessToken}")
                Log.d(KAKAO_LOGIN, "카카오 계정 로그인 성공 - 아이디 토큰: ${token.idToken} ")
                signIn("Bearer " + token.accessToken)
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.d(KAKAO_LOGIN, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.d(KAKAO_LOGIN, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    Log.d(KAKAO_LOGIN, "카카오 계정 로그인 성공 - 아이디 토큰: ${token.idToken} ")
                    signIn("Bearer " + token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun signIn(token: String) {
        ZoocStorage.token = token
        ZoocStorage.isUser = true
        postTokenToServer()
    }

    private fun goToOnBoarding() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun postTokenToServer() {
        authViewModel.postSignUp()
    }

    private fun storeToken() {
        authViewModel.signUpResult.observe(this) {
            ZoocStorage.token = it.data.jwtToken
            goToOnBoarding()
        }
    }
}
