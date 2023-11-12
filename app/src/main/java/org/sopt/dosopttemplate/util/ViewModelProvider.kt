package org.sopt.dosopttemplate.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.repository.AuthRepository

class AuthViewModelFactory(private val authRepository: AuthRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AuthRepository::class.java).newInstance(authRepository)
    }
}