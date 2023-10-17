package com.android.cleanarc.domain.model.otp


data class OTPData(
    val `data`: Data,
    val msg: String,
    val status: Boolean
)
data class Data(
    val email: String,
    val is_registered: Boolean,
    val length: Int,
    val OTP_id: Int
)