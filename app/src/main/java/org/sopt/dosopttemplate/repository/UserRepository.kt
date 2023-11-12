package org.sopt.dosopttemplate.repository

import org.sopt.dosopttemplate.db.remote.AuthApiHelper
import org.sopt.dosopttemplate.db.remote.UserApiHelper
import org.sopt.dosopttemplate.model.dto.RespResult
import org.sopt.dosopttemplate.model.dto.resp.auth.SignUpResp

class UserRepository(private val userApiHelper: UserApiHelper) {
    suspend fun getUserList(page: Int) = userApiHelper.getUserList(page)
}