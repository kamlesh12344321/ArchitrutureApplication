package com.android.cleanarc.data.source

import com.android.cleanarc.domain.model.otp.OTPData
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {
    @GET("/send-email-otp")
    suspend fun getOTPData(@Body otpRawData:Map<String, String>): OTPData

}