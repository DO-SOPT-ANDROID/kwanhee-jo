package org.sopt.dosopttemplate.model.dto.resp

sealed class SignUpResp {
    data class Success(val location: String): SignUpResp()
    data class Error(val message: String): SignUpResp()
}