package org.sopt.zooczoocbbangbbang.util

import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideExt {
    fun loadIntImage(image: Int, view: ImageView) {
        Glide.with(view).load(image).into(view)
    }

    fun loadStringImage(image: String, view: ImageView) {
        Glide.with(view).load(image).into(view)
    }
}
