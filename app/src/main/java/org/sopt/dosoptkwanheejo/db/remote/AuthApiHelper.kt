package org.sopt.dosoptkwanheejo.db.remote

import org.sopt.dosoptkwanheejo.api.AuthAPI
import org.sopt.dosoptkwanheejo.db.local.PreferenceManager
import org.sopt.dosoptkwanheejo.db.remote.RetrofitManager.BASE_URL
import org.sopt.dosoptkwanheejo.model.dto.RespResult
import org.sopt.dosoptkwanheejo.model.dto.req.LoginReq
import org.sopt.dosoptkwanheejo.model.dto.req.SignUpReq
import org.sopt.dosoptkwanheejo.model.dto.resp.auth.LoginResp
import org.sopt.dosoptkwanheejo.model.dto.resp.auth.SignUpResp
import org.sopt.dosoptkwanheejo.util.toErrorResult
import retrofit2.Call
import retrofit2.Response

class AuthApiHelper(private val authAPI: AuthAPI) {
    private val preferenceManager = PreferenceManager()

    fun signUp(
        id: String,
        nickname: String,
        password: String,
        onResponse: (SignUpResp) -> Unit
    ) {
        authAPI.signUp(SignUpReq(id, nickname, password))
            .enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>
                ) {
                    if (response.isSuccessful) {
                        onResponse(
                            SignUpResp.Success(
                                response.headers()["Location"]?.split("/")?.last().toString()
                            )
                        )
                    } else {
                        onResponse(
                            SignUpResp.Error(
                                response.errorBody()?.toErrorResult(BASE_URL)?.message.toString()
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {}
            })
    }

    fun login(id: String, password: String, auto: Boolean, onResponse: (RespResult) -> Unit) {
        authAPI.login(LoginReq(id, password))
            .enqueue(object : retrofit2.Callback<LoginResp> {
                override fun onResponse(call: Call<LoginResp>, response: Response<LoginResp>) {
                    if (response.isSuccessful) {
                        preferenceManager.run {
                            setAutoLogin(auto)
                            setAccount(response.body()?.username.toString())
                            setNickname(response.body()?.nickname.toString())
                        }
                        response.body()?.let {
                            onResponse(it)
                        }
                    } else {
                        response.errorBody()?.toErrorResult(BASE_URL)?.let {
                            onResponse(it)
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResp>, t: Throwable) {}
            })
    }
}





