package org.sopt.zooczoocbbangbbang.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.common.util.Utility
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityMainBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.home.HomeFragment
import org.sopt.zooczoocbbangbbang.presentation.main.mypage.MyPageFragment
import org.sopt.zooczoocbbangbbang.presentation.main.record.RecordActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val fragments = listOf(HomeFragment(), MyPageFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
        clickRecording()
        var keyHash = Utility.getKeyHash(this)
        Log.d("TOKEN", "keyhash : $keyHash")
    }

    private fun initBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.menu_home -> transaction.replace(R.id.fcv_main, fragments[0])
                R.id.menu_mypage -> transaction.replace(R.id.fcv_main, fragments[1])
            }
            transaction.commit()
            true
        }
    }

    private fun clickRecording() {
        binding.fabMain.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }
    }
}
