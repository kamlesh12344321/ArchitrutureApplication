package com.android.cleanarc.domain.usecase.base

import com.android.cleanarc.domain.exception.ApiException

interface UseCaseResponse<Type> {
    fun onSuccess(type: Type)
    fun onError(apiError: ApiException)

    fun onLoading(isLoading: Boolean)
}