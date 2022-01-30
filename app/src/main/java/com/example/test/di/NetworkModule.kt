package com.example.test.di

import com.example.test.BuildConfig
import com.example.test.data.network.BackendApi
import com.example.test.data.network.BackendApiImpl
import com.example.test.data.network.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://randomuser.me/"

val networkModule = module {
    single { getLoggingInterceptor() }
    single { getOkHttpClient(get()) }
    single { getRetrofit(get()) }
    single { getBackendApi(get()) }
    single { BackendApiImpl(service = get()) }

    single { getUserService(get()) }
}

private fun getLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

private fun getOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun getBackendApi(retrofit: Retrofit): BackendApi {
    return retrofit.create(BackendApi::class.java)
}

private fun getUserService(retrofit: Retrofit): UserService {
    return retrofit.create(UserService::class.java)
}

