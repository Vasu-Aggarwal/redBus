package com.example.redbus.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class viewModel: ViewModel() {

    val currentLoc = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = currentLoc

    fun selectItem(loc: String){
        currentLoc.value = loc
    }
}