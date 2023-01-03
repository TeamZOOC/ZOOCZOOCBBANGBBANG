package org.sopt.zooczoocbbangbbang.util

import android.content.Context
import android.widget.Toast

object ContextExt {
    private var toast: Toast? = null

    fun Context.shortToast(message: String) {
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(message)
        }
        toast?.show()
    }

    fun Context.longToast(message: String) {
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        } else {
            toast!!.setText(message)
        }
        toast?.show()
    }
}
