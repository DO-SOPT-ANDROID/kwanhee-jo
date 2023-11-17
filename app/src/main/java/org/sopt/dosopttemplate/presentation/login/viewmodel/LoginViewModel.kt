package org.sopt.dosopttemplate.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.model.dto.RespResult
import org.sopt.dosopttemplate.repository.AuthRepository

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _loginResp = MutableLiveData<RespResult>()
    val loginResp: LiveData<RespResult> = _loginResp

    fun login(id: String, password: String, auto: Boolean) {
        authRepository.login(id, password, auto) {
            _loginResp.value = it
        }
    }
}