package com.android.cleanarc.data.repository

import com.android.cleanarc.data.source.ApiService
import com.android.cleanarc.domain.model.otp.OTPData
import com.android.cleanarc.domain.repository.otp.OTPRepository

class OTPRepositoryImpl(private val apiService: ApiService): OTPRepository{
    override suspend fun getOTPData(otpRawData:Map<String, String>): OTPData {
        return apiService.getOTPData(otpRawData)
    }
}