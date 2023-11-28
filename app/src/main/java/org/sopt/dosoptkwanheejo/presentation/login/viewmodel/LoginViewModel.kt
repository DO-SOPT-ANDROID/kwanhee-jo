package org.sopt.dosoptkwanheejo.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosoptkwanheejo.model.dto.RespResult
import org.sopt.dosoptkwanheejo.repository.AuthRepository

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val idFlag = MutableLiveData<Boolean>()
    val passwordFlag = MutableLiveData<Boolean>()

    private val _loginResp = MutableLiveData<RespResult>()
    val loginResp: LiveData<RespResult> = _loginResp

    fun login(id: String, password: String, auto: Boolean) {
        authRepository.login(id, password, auto) {
            _loginResp.value = it
        }
    }
}