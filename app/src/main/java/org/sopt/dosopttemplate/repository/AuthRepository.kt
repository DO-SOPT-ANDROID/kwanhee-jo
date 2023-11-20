package org.sopt.dosopttemplate.repository

import org.sopt.dosopttemplate.db.remote.AuthApiHelper
import org.sopt.dosopttemplate.model.dto.RespResult
import org.sopt.dosopttemplate.model.dto.resp.auth.SignUpResp

class AuthRepository(private val authApiHelper: AuthApiHelper) {
    fun signUp(id: String, nickname: String, password: String, onResponse: (SignUpResp) -> Unit) =
        authApiHelper.signUp(id, nickname, password, onResponse)
    fun login(id: String, password: String, auto: Boolean, onResponse: (RespResult) -> Unit) =
        authApiHelper.login(id, password, auto, onResponse)
}