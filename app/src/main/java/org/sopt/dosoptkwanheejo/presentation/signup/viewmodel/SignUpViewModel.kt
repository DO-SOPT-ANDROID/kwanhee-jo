package org.sopt.dosoptkwanheejo.presentation.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sopt.dosoptkwanheejo.model.dto.resp.auth.SignUpResp
import org.sopt.dosoptkwanheejo.repository.AuthRepository

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val idFlag = MutableLiveData<Boolean>()
    val passwordFlag = MutableLiveData<Boolean>()

    private val _loginState = MutableSharedFlow<LoginState>()
    val loginState: SharedFlow<LoginState> = _loginState.asSharedFlow()

    fun signUp(id: String, nickname: String, password: String) {
        val scope = viewModelScope.launch { }
        authRepository.signUp(id, nickname, password) {
            when (it) {
                is SignUpResp.Success -> {
                    viewModelScope.launch {
                        _loginState.emit(LoginState.SUCCESS)
                    }
                }

                is SignUpResp.Error -> {
                    viewModelScope.launch {
                        _loginState.emit(LoginState.ERROR.apply {
                            message = it.message
                        })
                    }
                }
            }
        }
    }
}

enum class LoginState(
    var message: String
) {
    LOADING(""),
    SUCCESS(""),
    ERROR("")
}