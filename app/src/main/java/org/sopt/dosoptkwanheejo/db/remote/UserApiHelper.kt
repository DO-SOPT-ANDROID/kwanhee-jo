package org.sopt.dosoptkwanheejo.db.remote

import org.sopt.dosoptkwanheejo.api.UserAPI
import org.sopt.dosoptkwanheejo.model.dto.resp.user.UserResp
import retrofit2.Call
import retrofit2.Response

class UserApiHelper(private val userAPI: UserAPI) {
    fun getUserList(page: Int, onResponse: (Boolean, UserResp) -> Unit) {
        userAPI.getUserList(page).enqueue(object : retrofit2.Callback<UserResp> {
            override fun onResponse(call: Call<UserResp>, response: Response<UserResp>) {
                if (response.isSuccessful) {
                    onResponse(response.isSuccessful, response.body() ?: UserResp())
                } else {
                    onResponse(response.isSuccessful, response.body() ?: UserResp())
                }
            }

            override fun onFailure(call: Call<UserResp>, t: Throwable) {}
        })
    }
}