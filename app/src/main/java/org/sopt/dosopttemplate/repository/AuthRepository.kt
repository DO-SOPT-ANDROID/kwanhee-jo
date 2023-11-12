package org.sopt.dosopttemplate.repository

import org.sopt.dosopttemplate.db.remote.ApiHelper
import org.sopt.dosopttemplate.model.dto.RespResult
import org.sopt.dosopttemplate.model.dto.resp.SignUpResp

class AuthRepository(private val apiHelper: ApiHelper) {
    suspend fun signUp(id: String, nickname: String, password: String, onResponse: (SignUpResp) -> Unit) =
        apiHelper.signUp(id, nickname, password, onResponse)
    suspend fun login(id: String, password: String, auto: Boolean, onResponse: (RespResult) -> Unit) =
        apiHelper.login(id, password, auto, onResponse)
}