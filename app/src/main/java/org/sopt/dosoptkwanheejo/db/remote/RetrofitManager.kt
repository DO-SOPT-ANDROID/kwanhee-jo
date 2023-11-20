package org.sopt.dosoptkwanheejo.db.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosoptkwanheejo.BuildConfig
import org.sopt.dosoptkwanheejo.BuildConfig.BASE_URL
import org.sopt.dosoptkwanheejo.api.AuthAPI
import org.sopt.dosoptkwanheejo.api.NaverAPI
import org.sopt.dosoptkwanheejo.api.UserAPI
import org.sopt.dosoptkwanheejo.db.remote.RetrofitManager.NAVER_PAPAGO_BASE_URL
import org.sopt.dosoptkwanheejo.db.remote.RetrofitManager.USER_BASE_URL
import retrofit2.Retrofit

object RetrofitManager {
    const val BASE_URL = BuildConfig.BASE_URL
    const val USER_BASE_URL = BuildConfig.USER_BASE_URL
    const val NAVER_PAPAGO_BASE_URL = BuildConfig.NAVER_PAPAGO_BASE_URL

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor { it: Interceptor.Chain ->
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
                .build()

            it.proceed(request)
        }
        .build()

    fun getRetrofit(url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    inline fun <reified T, B> create(url: B): T =
        getRetrofit(url.toString()).create<T>(T::class.java)
}

object RetrofitServicePool {
    val authService = RetrofitManager.create<AuthAPI, String>(BASE_URL)
    val userService = RetrofitManager.create<UserAPI, String>(USER_BASE_URL)
    val naverPapagoService = RetrofitManager.create<NaverAPI, String>(NAVER_PAPAGO_BASE_URL)
}