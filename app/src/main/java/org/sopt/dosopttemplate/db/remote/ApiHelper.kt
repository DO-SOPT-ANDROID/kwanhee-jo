package org.sopt.dosopttemplate.db.remote

import org.sopt.dosopttemplate.api.RetrofitService
import org.sopt.dosopttemplate.model.dto.req.SignUpReq
import org.sopt.dosopttemplate.model.dto.resp.SignUpResp
import org.sopt.dosopttemplate.util.toErrorResult
import retrofit2.Call
import retrofit2.Response

class ApiHelper(private val retrofitService: RetrofitService) {
    fun signUp(
        id: String,
        nickname: String,
        password: String,
        onResponse: (SignUpResp) ->  Unit
    ) {
        retrofitService.signUp(SignUpReq(id, nickname, password))
            .enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>
                ) {
                    if (response.isSuccessful && response.code() == 201) {
                        onResponse(SignUpResp.Success(response.headers()["Location"]?.split("/")?.last().toString()))
                    } else {
                        onResponse(SignUpResp.Error(response.errorBody()?.toErrorResult()?.message.toString()))
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}





