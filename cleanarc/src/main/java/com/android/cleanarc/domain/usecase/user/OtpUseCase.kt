package com.android.cleanarc.domain.usecase.user

import com.android.cleanarc.domain.model.otp.OTPData
import com.android.cleanarc.domain.repository.otp.OTPRepository
import com.android.cleanarc.domain.usecase.base.UseCase

@Suppress("UNCHECKED_CAST")
class OtpUseCase(private val otpRepository: OTPRepository) : UseCase<OTPData, Any>() {
    override suspend fun run(params: Any?): OTPData {
        return otpRepository.getOTPData(params as Map<String, String>)
    }
}