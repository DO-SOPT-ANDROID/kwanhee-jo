package org.sopt.dosopttemplate.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.model.HomeBottomItem

class HomeViewModel: ViewModel() {
    private val _bottomItemId = MutableLiveData<HomeBottomItem>()
    val bottomItemId: LiveData<HomeBottomItem> = _bottomItemId

    fun setBottomItemId(id: HomeBottomItem) {
        _bottomItemId.value = id
    }
}