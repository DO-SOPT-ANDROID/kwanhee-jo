package org.sopt.dosoptkwanheejo.model.dto.resp.auth

sealed class SignUpResp {
    data class Success(val location: String): SignUpResp()
    data class Error(val message: String): SignUpResp()
}