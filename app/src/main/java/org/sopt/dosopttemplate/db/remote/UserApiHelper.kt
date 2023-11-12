package org.sopt.dosopttemplate.db.remote

import android.util.Log
import org.sopt.dosopttemplate.api.AuthAPI
import org.sopt.dosopttemplate.api.UserAPI
import org.sopt.dosopttemplate.model.dto.resp.user.UserResp
import retrofit2.Call
import retrofit2.Response

class UserApiHelper(private val userAPI: UserAPI) {
    fun getUserList(page: Int) {
        userAPI.getUserList(page).enqueue(object: retrofit2.Callback<UserResp> {
            override fun onResponse(call: Call<UserResp>, response: Response<UserResp>) {
                if (response.isSuccessful && response.code() == 200) {
                    Log.e("로그", "${response.body()}")
                } else {

                }
            }

            override fun onFailure(call: Call<UserResp>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}