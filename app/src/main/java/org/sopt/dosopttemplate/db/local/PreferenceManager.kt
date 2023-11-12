package org.sopt.dosopttemplate.db.local

import androidx.core.content.edit
import org.sopt.dosopttemplate.DoSoptApp.Companion.sharedPreferencesInstance
import org.sopt.dosopttemplate.model.User

class PreferenceManager {
    fun setAutoLogin(auto: Boolean) {
        sharedPreferencesInstance.edit(commit = true) {
            putBoolean(AUTO_LOGIN, auto)
        }
    }

    fun setAccount(account: String) {
        sharedPreferencesInstance.edit(commit = true) {
            putString(ID, account)
        }
    }

    fun setNickname(nickname: String) {
        sharedPreferencesInstance.edit(commit = true) {
            putString(NICKNAME, nickname)
        }
    }

    fun setMBTI(mbti: String) {
        sharedPreferencesInstance.edit(commit = true) {
            putString(MBTI, mbti)
        }
    }

    companion object {
        const val EXTRA_USER = "USER"
        const val ID = "ID"
        const val PWD = "PWD"
        const val NICKNAME = "NICKNAME"
        const val MBTI = "MBTI"
        const val AUTO_LOGIN = "AUTO_LOGIN"
    }
}