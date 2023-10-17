package com.android.cleanarc.data.network

import com.android.cleanarc.data.source.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .addInterceptor { chain -> chain.proceed(//create request
            chain.request()
                .newBuilder()
                //add headers to the request builder
                .also {
                    it.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjbGllbnRfaWQiOjEsInByb3BlcnR5X2lkIjoxLCJ0aW1lIjoxNjc5MDU0MTQwfQ.rJn83qJutwRyeTY5-F76BBhH3_sMY3vXPRe8Xu64QZU")
                }
                .build()) }
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)

        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {

    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun  createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
//
////UseCases
//fun  createOtpDataUseCase(postsRepository: OtpDataRepository): GetOtpDataUseCase {
//    return GetOtpDataUseCase(postsRepository)
//}
//
//
//// Repository
//fun  createOtpDataRepository(apiService: ApiService): OtpDataRepository {
//    return OtpDataRepositoryImpl(apiService)
//}
