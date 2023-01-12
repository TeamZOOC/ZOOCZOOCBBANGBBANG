package org.sopt.zooczoocbbangbbang.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import org.sopt.zooczoocbbangbbang.data.local.ZoocStorage

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val headerRequest = originalRequest.newBuilder()
            .header(
                "Authorization",
                ZoocStorage.token ?: throw IllegalArgumentException("토큰 없어 망했어")
            )
            .build()
        return chain.proceed(headerRequest)
    }
}
