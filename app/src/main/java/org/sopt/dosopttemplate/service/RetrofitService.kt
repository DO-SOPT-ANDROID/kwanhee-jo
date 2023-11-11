package org.sopt.dosopttemplate.service

import org.sopt.dosopttemplate.model.dto.req.SignUpReq
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("api/v1/members")
    fun signup(
        @Body request: SignUpReq,
    ): Call<Unit>
}