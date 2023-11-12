package org.sopt.dosopttemplate.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.BuildConfig
import org.sopt.dosopttemplate.service.RetrofitService
import retrofit2.Retrofit

object RetrofitManager {
    private const val BASE_URL = BuildConfig.BASE_URL

//    var httpLogginInterceptor = HttpLoggingInterceptor()
//        .setLevel(HttpLoggingInterceptor.Level.BODY)
//
//    var okHttpClient = OkHttpClient
//        .Builder()
//        .addInterceptor(httpLogginInterceptor)
//        .build()

    val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object RetrofitServicePool {
    val authService = RetrofitManager.create<RetrofitService>()
}