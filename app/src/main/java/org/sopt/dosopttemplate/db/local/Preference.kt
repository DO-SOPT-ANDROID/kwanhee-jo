package org.sopt.dosopttemplate.db.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.model.User

class Preference(val context: Context) {
    fun getId(): String = context.getSharedPreferences().getString(ID, "") ?: ""
    fun getPassword(): String = context.getSharedPreferences().getString(PWD, "") ?: ""
    fun getNickname(): String = context.getSharedPreferences().getString(NICKNAME, "") ?: ""
    fun getMBTI(): String = context.getSharedPreferences().getString(MBTI, "") ?: ""
    fun getAutoLogin(): Boolean = context.getSharedPreferences().getBoolean(AUTO_LOGIN, false)

    fun setAutoLogin(auto: Boolean) {
        context.getSharedPreferences().edit(commit = true) {
            putBoolean(AUTO_LOGIN, auto)
        }
    }

    fun setUser(user: User?) {
        context.getSharedPreferences().edit(commit = true) {
            user?.let {
                putString(ID, it.id)
                putString(PWD, it.password)
                putString(NICKNAME, it.nickname)
                putString(MBTI, it.mbti.toString())
            }
        }
    }

    private fun Context.getSharedPreferences(): SharedPreferences =
        getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    companion object {
        const val EXTRA_USER = "USER"
        const val ID = "ID"
        const val PWD = "PWD"
        const val NICKNAME = "NICKNAME"
        const val MBTI = "MBTI"
        const val AUTO_LOGIN = "AUTO_LOGIN"
    }
}
