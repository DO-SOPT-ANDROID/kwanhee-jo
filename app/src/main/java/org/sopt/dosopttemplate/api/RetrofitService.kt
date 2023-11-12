package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.model.dto.req.SignUpReq
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("api/v1/members")
    fun signUp(
        @Body request: SignUpReq,
    ): Call<Unit>
}