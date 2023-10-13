package com.android.mvi.data.api

import com.android.mvi.data.model.Data

interface ApiHelper {
    suspend fun getUsers(): Data
}