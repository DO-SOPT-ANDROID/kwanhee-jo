package org.sopt.dosoptkwanheejo.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosoptkwanheejo.repository.AuthRepository
import org.sopt.dosoptkwanheejo.repository.NaverRepository
import org.sopt.dosoptkwanheejo.repository.UserRepository

class AuthViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AuthRepository::class.java).newInstance(authRepository)
    }
}

class HomeViewModelFactory(
    private val userRepository: UserRepository,
    private val naverRepository: NaverRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java, NaverRepository::class.java)
            .newInstance(userRepository, naverRepository)
    }
}

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java).newInstance(userRepository)
    }
}
