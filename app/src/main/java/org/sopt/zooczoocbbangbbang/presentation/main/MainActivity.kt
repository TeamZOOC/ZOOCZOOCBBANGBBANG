package org.sopt.zooczoocbbangbbang.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.data.local.ZoocStorage
import org.sopt.zooczoocbbangbbang.databinding.ActivityMainBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity
import org.sopt.zooczoocbbangbbang.presentation.main.home.HomeFragment
import org.sopt.zooczoocbbangbbang.presentation.main.mypage.MyPageFragment
import org.sopt.zooczoocbbangbbang.presentation.main.ymrecord.YmRecordActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
        clickRecording()
        Log.d("my token", "${ZoocStorage.token}")
    }

    private fun initBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.menu_home -> transaction.replace(R.id.fcv_main, HomeFragment())
                R.id.menu_mypage -> transaction.replace(R.id.fcv_main, MyPageFragment())
            }
            transaction.commit()
            true
        }
    }

    private fun clickRecording() {
        binding.fabMain.setOnClickListener {
            val intent = Intent(this, YmRecordActivity::class.java)
            startActivity(intent)
        }
    }
}
