package com.android.cleanarc.domain.usecase.base

import com.android.cleanarc.domain.exception.traceErrorException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class UseCase<Type, in Params>() where Type : Any {

    abstract suspend fun run(params: Params? = null): Type

    suspend operator fun invoke(
        scope: CoroutineScope,
        params: Params?,
        onResponse: UseCaseResponse<Type>?
    ) {

        scope.launch {
            onResponse?.onLoading(true)
            try {
                val result = run(params)
                onResponse?.onLoading(false)
                onResponse?.onSuccess(result)
            } catch (e: Exception) {
                e.printStackTrace()
                onResponse?.onLoading(false)
                onResponse?.onError(traceErrorException(e))
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResponse?.onLoading(false)
                onResponse?.onError(traceErrorException(e))
            }
        }
    }
}