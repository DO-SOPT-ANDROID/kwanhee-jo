package org.sopt.dosoptkwanheejo.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosoptkwanheejo.model.HomeBottomItem
import org.sopt.dosoptkwanheejo.model.dto.resp.user.UserResp
import org.sopt.dosoptkwanheejo.repository.UserRepository

class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _bottomItemId = MutableLiveData<HomeBottomItem>()
    val bottomItemId: LiveData<HomeBottomItem> = _bottomItemId

    private val _userList = MutableLiveData<UserResp>()
    val userList: LiveData<UserResp> = _userList

    fun setBottomItemId(id: HomeBottomItem) {
        _bottomItemId.value = id
    }

    fun getUserList(page: Int = 1) {
        viewModelScope.launch {
            userRepository.getUserList(page) { isSuccess, userResp ->
                _userList.value = userResp
            }
        }
    }
}