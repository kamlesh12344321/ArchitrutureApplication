package com.android.mvi.data.api

import com.android.mvi.data.model.Data
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Data
}