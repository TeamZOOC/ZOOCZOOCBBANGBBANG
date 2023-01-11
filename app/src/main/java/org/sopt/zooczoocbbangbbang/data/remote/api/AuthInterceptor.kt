package org.sopt.zooczoocbbangbbang.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val headerRequest = originalRequest.newBuilder()
            .header(
                "token",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjYsImlhdCI6MTY3Mjk0NTE3MSwiZXhwIjoxNjczNTQ5OTcxfQ.u0wbFcohn0YlmGW_AeAq1I3LSC-y3oUtFHp9vGA_vbw"
            )
            .build()
        return chain.proceed(headerRequest)
    }
}
