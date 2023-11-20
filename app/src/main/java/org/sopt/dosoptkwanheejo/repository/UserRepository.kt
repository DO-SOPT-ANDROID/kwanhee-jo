package org.sopt.dosoptkwanheejo.repository

import org.sopt.dosoptkwanheejo.db.remote.UserApiHelper
import org.sopt.dosoptkwanheejo.model.dto.resp.user.UserResp

class UserRepository(private val userApiHelper: UserApiHelper) {
    suspend fun getUserList(page: Int, onResponse: (Boolean, UserResp) -> Unit) = userApiHelper.getUserList(page, onResponse)
}