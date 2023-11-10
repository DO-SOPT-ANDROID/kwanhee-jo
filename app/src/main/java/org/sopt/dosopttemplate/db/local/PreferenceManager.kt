package org.sopt.dosopttemplate.db.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.model.User

class PreferenceManager(val context: Context) {
    init {
        getInstance(context)
    }
    fun getId(): String = sharedPreferencesInstance.getString(ID, "").orEmpty()
    fun getPassword(): String = sharedPreferencesInstance.getString(PWD, "").orEmpty()
    fun getNickname(): String = sharedPreferencesInstance.getString(NICKNAME, "").orEmpty()
    fun getMBTI(): String = sharedPreferencesInstance.getString(MBTI, "").orEmpty()
    fun getAutoLogin(): Boolean = sharedPreferencesInstance.getBoolean(AUTO_LOGIN, false)

    fun setAutoLogin(auto: Boolean) {
        sharedPreferencesInstance.edit(commit = true) {
            putBoolean(AUTO_LOGIN, auto)
        }
    }

    fun setUser(user: User?) {
        sharedPreferencesInstance.edit(commit = true) {
            user?.let {
                putString(ID, it.id)
                putString(PWD, it.password)
                putString(NICKNAME, it.nickname)
                putString(MBTI, it.mbti.toString())
            }
        }
    }

    companion object {
        const val EXTRA_USER = "USER"
        const val ID = "ID"
        const val PWD = "PWD"
        const val NICKNAME = "NICKNAME"
        const val MBTI = "MBTI"
        const val AUTO_LOGIN = "AUTO_LOGIN"

        lateinit var sharedPreferencesInstance: SharedPreferences

        @Synchronized
        private fun getInstance(context: Context) {
            if (::sharedPreferencesInstance.isInitialized) {
                sharedPreferencesInstance = context.getSharedPreferences(
                    context.getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE
                )
            }
        }
    }
}