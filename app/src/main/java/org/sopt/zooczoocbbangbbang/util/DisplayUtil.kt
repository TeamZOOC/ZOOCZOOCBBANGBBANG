package org.sopt.zooczoocbbangbbang.util

import android.content.Context
import kotlin.math.roundToInt

object DisplayUtil {
    fun dpToPx(context: Context, dp: Int): Int {
        val density: Float = context.resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }

    fun pxToDp(context: Context, px: Int): Int {
        val density = context.resources.displayMetrics.density
        return (px / density).toInt()
    }
}
