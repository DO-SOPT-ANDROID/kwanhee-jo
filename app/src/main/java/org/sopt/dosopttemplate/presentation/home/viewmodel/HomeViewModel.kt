package org.sopt.dosopttemplate.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.model.HomeBottomItem
import org.sopt.dosopttemplate.model.HomeProfileModel

class HomeViewModel : ViewModel() {
    private val _bottomItemId = MutableLiveData<HomeBottomItem>()
    val bottomItemId: LiveData<HomeBottomItem> = _bottomItemId

    private val _profileList = MutableLiveData<List<HomeProfileModel>>()
    val profileList: LiveData<List<HomeProfileModel>> = _profileList

    fun setBottomItemId(id: HomeBottomItem) {
        _bottomItemId.value = id
    }

    fun setProfileList(profileList: List<HomeProfileModel>) {
        _profileList.value = profileList
    }
}