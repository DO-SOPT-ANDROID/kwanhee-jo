package org.sopt.dosoptkwanheejo.api

import org.sopt.dosoptkwanheejo.model.dto.req.LoginReq
import org.sopt.dosoptkwanheejo.model.dto.req.SignUpReq
import org.sopt.dosoptkwanheejo.model.dto.resp.auth.LoginResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("api/v1/members")
    fun signUp(
        @Body signUpReq: SignUpReq,
    ): Call<Unit>
    @POST("api/v1/members/sign-in")
    fun login(
        @Body loginReq: LoginReq
    ): Call<LoginResp>
}