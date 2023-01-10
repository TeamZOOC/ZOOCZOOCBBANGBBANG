package org.sopt.zooczoocbbangbbang.presentation.detail

import android.os.Bundle
import org.sopt.zooczoocbbangbbang.R
import org.sopt.zooczoocbbangbbang.databinding.ActivityDetailBinding
import org.sopt.zooczoocbbangbbang.presentation.base.BindingActivity

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
