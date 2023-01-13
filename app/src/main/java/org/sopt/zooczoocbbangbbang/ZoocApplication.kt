package org.sopt.zooczoocbbangbbang

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import org.sopt.zooczoocbbangbbang.data.local.ZoocStorage

class ZoocApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ZoocStorage.init(this)
        KakaoSdk.init(this, "444abf8009d07e98dbd0ad87ffca4ca3")
    }
}
