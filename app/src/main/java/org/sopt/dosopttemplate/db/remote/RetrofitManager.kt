package org.sopt.dosopttemplate.db.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.BuildConfig
import org.sopt.dosopttemplate.BuildConfig.BASE_URL
import org.sopt.dosopttemplate.BuildConfig.USER_BASE_URL
import org.sopt.dosopttemplate.api.AuthAPI
import org.sopt.dosopttemplate.api.UserAPI
import retrofit2.Retrofit

object RetrofitManager {
    const val BASE_URL = BuildConfig.BASE_URL
    const val USER_BASE_URL = BuildConfig.BASE_URL

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    fun getRetrofit(url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

//    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
    inline fun <reified T, B> create(url: B): T = getRetrofit(url.toString()).create<T>(T::class.java)
}

object RetrofitServicePool {
    val authService = RetrofitManager.create<AuthAPI, String>(BASE_URL)
    val userService = RetrofitManager.create<UserAPI, String>(USER_BASE_URL)
}