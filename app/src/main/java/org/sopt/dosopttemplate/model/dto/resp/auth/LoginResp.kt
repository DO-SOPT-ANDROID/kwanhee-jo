package org.sopt.dosopttemplate.model.dto.resp.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.model.dto.RespResult

@Serializable
data class LoginResp(
    @SerialName("id")
    val id: Int,
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String
): RespResult()
