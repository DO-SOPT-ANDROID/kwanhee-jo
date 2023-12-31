package org.sopt.dosoptkwanheejo.presentation.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosoptkwanheejo.model.dto.resp.auth.SignUpResp
import org.sopt.dosoptkwanheejo.repository.AuthRepository

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val idFlag = MutableLiveData<Boolean>()
    val passwordFlag = MutableLiveData<Boolean>()

    private val _signUpResp = MutableLiveData<SignUpResp>()
    val signUpResp: LiveData<SignUpResp> = _signUpResp

    fun signUp(id: String, nickname: String, password: String) {
        authRepository.signUp(id, nickname, password) {
            _signUpResp.value = it
        }
    }
}
