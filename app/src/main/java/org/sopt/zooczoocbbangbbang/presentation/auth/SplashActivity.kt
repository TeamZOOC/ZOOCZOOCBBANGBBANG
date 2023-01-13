package org.sopt.zooczoocbbangbbang.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.kakao.sdk.common.util.Utility
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.data.local.ZoocStorage
import org.sopt.zooczoocbbangbbang.databinding.ActivitySplashBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.MainActivity

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ZoocStorage.clear()
        var keyHash = Utility.getKeyHash(this)
        Log.d("가나다라마바샂", "keyhash : $keyHash")
        setDelay()
    }

    private fun setDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            checkUser()
        }, 1000L)
    }

    private fun checkUser() {
        val isUser = ZoocStorage.isUser
        if (!isUser) {
            startSignIn()
        } else {
            startMain()
        }
    }

    private fun startMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}
