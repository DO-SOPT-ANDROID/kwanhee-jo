package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.model.dto.resp.user.UserResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {
    @GET("api/users")
    fun getUserList(
        @Query("page") page: Int
    ): Call<UserResp>
}