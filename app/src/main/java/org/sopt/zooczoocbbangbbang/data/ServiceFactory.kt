package org.sopt.zooczoocbbangbbang.data

import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {
    private const val BASE_URL = "http://3.36.236.44:3000"

    /*val retrofitZooc: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }*/

    val json = Json {
        isLenient = true
        coerceInputValues = true
    }

    private val retrofitZooc: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val zoocService: ZoocService = retrofitZooc.create(ZoocService::class.java)
}
