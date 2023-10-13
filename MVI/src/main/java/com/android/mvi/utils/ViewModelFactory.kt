package com.android.mvi.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.mvi.data.api.ApiHelper

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(com.android.mvi.uis.viewmodel.DataViewModel::class.java)) {
            return com.android.mvi.uis.viewmodel.DataViewModel(com.android.mvi.data.repository.MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}