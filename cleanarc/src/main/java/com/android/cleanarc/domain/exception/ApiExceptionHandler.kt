package com.android.cleanarc.domain.exception

import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun traceErrorException(throwable: Throwable): ApiException {


    return when (throwable) {
        is HttpException -> {
            when (throwable.code()) {
                400 -> ApiException(
                    throwable.message(),
                    throwable.code(),
                    ApiException.ErrorStatus.BAD_REQUEST
                )

                401 -> ApiException(
                    throwable.message(),
                    throwable.code(),
                    ApiException.ErrorStatus.UNAUTHORIZED
                )

                403 -> ApiException(
                    throwable.message(),
                    throwable.code(),
                    ApiException.ErrorStatus.FORBIDDEN
                )

                404 -> ApiException(
                    throwable.message(),
                    throwable.code(),
                    ApiException.ErrorStatus.NOT_FOUND
                )

                405 -> ApiException(
                    throwable.message(),
                    throwable.code(),
                    ApiException.ErrorStatus.METHOD_NOT_ALLOWED
                )

                409 -> ApiException(
                    throwable.message(),
                    throwable.code(),
                    ApiException.ErrorStatus.CONFLICT
                )

                500 -> ApiException(
                    throwable.message(),
                    throwable.code(),
                    ApiException.ErrorStatus.INTERNAL_SERVER_ERROR
                )

                else -> ApiException(
                    UNKNOWN_ERROR_MESSAGE,
                    0,
                    ApiException.ErrorStatus.UNKNOWN_ERROR
                )
            }
        }
        is SocketTimeoutException -> {
            ApiException(throwable.message, ApiException.ErrorStatus.TIMEOUT)
        }
       is IOException -> {
            ApiException(throwable.message, ApiException.ErrorStatus.NO_CONNECTION)
        }
        else -> ApiException(UNKNOWN_ERROR_MESSAGE, 0, ApiException.ErrorStatus.UNKNOWN_ERROR)
    }
}