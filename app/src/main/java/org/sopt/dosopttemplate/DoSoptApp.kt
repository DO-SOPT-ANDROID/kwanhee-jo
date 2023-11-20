package org.sopt.dosopttemplate

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.sopt.dosopttemplate.db.remote.AuthApiHelper
import org.sopt.dosopttemplate.db.remote.RetrofitServicePool
import org.sopt.dosopttemplate.db.remote.UserApiHelper

class DoSoptApp : Application() {
    override fun onCreate() {
        super.onCreate()
        getSharedPreferencesInstance(this)
    }

    companion object {
        lateinit var sharedPreferencesInstance: SharedPreferences
        private lateinit var authApiHelper: AuthApiHelper
        private lateinit var userApiHelper: UserApiHelper

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
                authApiHelper = AuthApiHelper(RetrofitServicePool.authService)
            }
            return authApiHelper
        }

        fun getUserApiHelperInstance(): UserApiHelper {
            if (!::userApiHelper.isInitialized) {
                userApiHelper = UserApiHelper(RetrofitServicePool.userService)
            }
            return userApiHelper
        }
    }
}