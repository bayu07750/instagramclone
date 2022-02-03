package com.bayu.instagramhomepage.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _isBottomSheetShow = MutableStateFlow(false)
    val isBottomSheetShow: StateFlow<Boolean> = _isBottomSheetShow

    fun showBottomSheet() {
        _isBottomSheetShow.value = !_isBottomSheetShow.value
    }

    fun hideBottomSheet() {
        _isBottomSheetShow.value = false
    }

}