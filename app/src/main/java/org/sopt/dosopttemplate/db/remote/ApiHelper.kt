package org.sopt.dosopttemplate.db.remote

import org.sopt.dosopttemplate.api.RetrofitService
import org.sopt.dosopttemplate.db.local.PreferenceManager
import org.sopt.dosopttemplate.model.dto.RespResult
import org.sopt.dosopttemplate.model.dto.req.LoginReq
import org.sopt.dosopttemplate.model.dto.req.SignUpReq
import org.sopt.dosopttemplate.model.dto.resp.LoginResp
import org.sopt.dosopttemplate.model.dto.resp.SignUpResp
import org.sopt.dosopttemplate.util.toErrorResult
import retrofit2.Call
import retrofit2.Response

class ApiHelper(private val retrofitService: RetrofitService) {
    private val preferenceManager = PreferenceManager()

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

    fun login(id: String, password: String, auto: Boolean, onResponse: (RespResult) -> Unit) {
        retrofitService.login(LoginReq(id, password))
            .enqueue(object : retrofit2.Callback<LoginResp> {
                override fun onResponse(call: Call<LoginResp>, response: Response<LoginResp>) {
                    if (response.isSuccessful && response.code() == 200) {
                        preferenceManager.run {
                            setAutoLogin(auto)
                            setAccount(response.body()?.username.toString())
                            setNickname(response.body()?.nickname.toString())
                        }
                        response.body()?.let {
                            onResponse(it)
                        }
                    } else {
                        response.errorBody()?.toErrorResult()?.let {
                            onResponse(it)
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResp>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}





