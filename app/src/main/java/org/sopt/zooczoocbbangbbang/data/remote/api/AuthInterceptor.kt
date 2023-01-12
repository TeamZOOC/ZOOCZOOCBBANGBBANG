package org.sopt.zooczoocbbangbbang.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val headerRequest = originalRequest.newBuilder()
            .header(
                "Authorization",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTY3MzQ2MzMxMiwiZXhwIjoxNjc0MDY4MTEyfQ.AKlzlfMwUIBtiVORDzX7NpPcgCKwjtji9VJOuoqq7lc"
            )
            .build()
        return chain.proceed(headerRequest)
    }
}
