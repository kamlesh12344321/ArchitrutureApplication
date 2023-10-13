package com.android.mvi.uis.viewstates

import com.android.mvi.data.model.Data

sealed class DataState {
    object Inactive : DataState()
    object Loading : DataState()
    data class ResponseData(val data: Data) : DataState()
    data class Error(val error: String?) : DataState()
}