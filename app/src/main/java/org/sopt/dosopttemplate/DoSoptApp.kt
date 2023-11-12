package org.sopt.dosopttemplate

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.sopt.dosopttemplate.db.remote.ApiHelper
import org.sopt.dosopttemplate.db.remote.RetrofitServicePool

class DoSoptApp : Application() {
    override fun onCreate() {
        super.onCreate()
        getSharedPreferencesInstance(this)
    }

    companion object {
        lateinit var sharedPreferencesInstance: SharedPreferences
        private lateinit var apiHelper: ApiHelper

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

        fun getApiHelperInstance(): ApiHelper {
            if (!::apiHelper.isInitialized) {
                apiHelper = ApiHelper(RetrofitServicePool.authService)
            }
            return apiHelper
        }
    }
}