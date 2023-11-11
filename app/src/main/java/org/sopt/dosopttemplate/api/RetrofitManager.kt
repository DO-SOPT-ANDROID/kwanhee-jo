package org.sopt.dosopttemplate.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.sopt.dosopttemplate.BuildConfig
import org.sopt.dosopttemplate.service.RetrofitService
import retrofit2.Retrofit

object RetrofitManager {
    private const val BASE_URL = BuildConfig.BASE_URL

    val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object RetrofitServicePool {
    val authService = RetrofitManager.create<RetrofitService>()
}