package org.sopt.dosopttemplate.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.model.HomeBottomItem
import org.sopt.dosopttemplate.model.HomeProfileModel
import org.sopt.dosopttemplate.model.dto.resp.user.UserResp
import org.sopt.dosopttemplate.repository.UserRepository

class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _bottomItemId = MutableLiveData<HomeBottomItem>()
    val bottomItemId: LiveData<HomeBottomItem> = _bottomItemId

    private val _profileList = MutableLiveData<List<HomeProfileModel>>()
    val profileList: LiveData<List<HomeProfileModel>> = _profileList

    private val _userList = MutableLiveData<UserResp>()
    val userList: LiveData<UserResp> = _userList

    fun setBottomItemId(id: HomeBottomItem) {
        _bottomItemId.value = id
    }

    fun setProfileList(profileList: List<HomeProfileModel>) {
        _profileList.value = profileList
    }

    fun addProfileList(profile: HomeProfileModel.Profile) {
        _profileList.value = _profileList.value?.toMutableList()?.let {
            it.add(profile)
            it.toList()
        }
    }

    fun removeProfileList(position: Int) {
        _profileList.value = _profileList.value?.toMutableList()?.let {
            it.removeIf {
                when (it) {
                    is HomeProfileModel.ProfileHeader -> {
                        it.id == position
                    }

                    is HomeProfileModel.ProfileBirthday -> {
                        it.id == position
                    }

                    is HomeProfileModel.Profile -> {
                        it.id == position
                    }
                }
            }
            it.toList()
        }
    }

    fun addBirthdayProfile(profile: HomeProfileModel.ProfileBirthday) {
        _profileList.value = _profileList.value?.toMutableList()?.let {
            it.add(1, profile)
            it.toList()
        }
    }

    fun getUserList(page: Int = 1) {
        viewModelScope.launch {
            userRepository.getUserList(page) { isSuccess, userResp ->
                _userList.value = userResp
            }
        }
    }
}