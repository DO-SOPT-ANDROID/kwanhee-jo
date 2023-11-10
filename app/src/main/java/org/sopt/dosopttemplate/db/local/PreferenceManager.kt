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
    fun getId(): String = getInstance(context).getString(ID, "") ?: ""
    fun getPassword(): String = getInstance(context).getString(PWD, "") ?: ""
    fun getNickname(): String = getInstance(context).getString(NICKNAME, "") ?: ""
    fun getMBTI(): String = getInstance(context).getString(MBTI, "") ?: ""
    fun getAutoLogin(): Boolean = getInstance(context).getBoolean(AUTO_LOGIN, false)

    fun setAutoLogin(auto: Boolean) {
        getInstance(context).edit(commit = true) {
            putBoolean(AUTO_LOGIN, auto)
        }
    }

    fun setUser(user: User?) {
        getInstance(context).edit(commit = true) {
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

        private fun getInstance(context: Context): SharedPreferences = runBlocking {
            withContext(Dispatchers.IO) {
                Mutex().withLock {
                    context.getSharedPreferences(
                        context.getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE
                    )
                }
            }
        }
    }
}
