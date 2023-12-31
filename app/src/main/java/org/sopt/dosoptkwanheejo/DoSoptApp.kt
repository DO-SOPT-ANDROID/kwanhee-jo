package org.sopt.dosoptkwanheejo

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.sopt.dosoptkwanheejo.db.remote.AuthApiHelper
import org.sopt.dosoptkwanheejo.db.remote.NaverApiHelper
import org.sopt.dosoptkwanheejo.db.remote.RetrofitServicePool
import org.sopt.dosoptkwanheejo.db.remote.UserApiHelper

class DoSoptApp : Application() {
    override fun onCreate() {
        super.onCreate()
        getSharedPreferencesInstance(this)
    }

    companion object {
        lateinit var sharedPreferencesInstance: SharedPreferences
        private lateinit var authApiHelper: AuthApiHelper
        private lateinit var userApiHelper: UserApiHelper
        private lateinit var naverApiHelper: NaverApiHelper


        @Synchronized
        private fun getSharedPreferencesInstance(context: Context): SharedPreferences {
            if (!::sharedPreferencesInstance.isInitialized) {
                sharedPreferencesInstance = context.getSharedPreferences(
                    context.getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE
                )
            }
            return sharedPreferencesInstance
        }

        fun getApiHelperInstance(): AuthApiHelper {
            if (!::authApiHelper.isInitialized) {
                try {
                    authApiHelper = AuthApiHelper(RetrofitServicePool.authService)
                } catch (e: ExceptionInInitializerError) {
                    e.printStackTrace()
                }
            }
            return authApiHelper
        }

        fun getUserApiHelperInstance(): UserApiHelper {
            if (!::userApiHelper.isInitialized) {
                userApiHelper = UserApiHelper(RetrofitServicePool.userService)
            }
            return userApiHelper
        }

        fun getNaverApiHelperInstance(): NaverApiHelper {
            if (!::naverApiHelper.isInitialized) {
                naverApiHelper = NaverApiHelper(RetrofitServicePool.naverPapagoService)
            }
            return naverApiHelper
        }
    }
}