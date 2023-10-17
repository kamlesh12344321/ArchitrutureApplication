package com.android.cleanarc.domain.repository.otp

import com.android.cleanarc.domain.model.otp.OTPData

interface OTPRepository {
    suspend fun getOTPData(otpRawData:Map<String, String>): OTPData
}