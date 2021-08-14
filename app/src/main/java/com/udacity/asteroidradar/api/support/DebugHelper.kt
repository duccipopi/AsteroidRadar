package com.udacity.asteroidradar.api.support

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

const val isDebugEnabled = true

val httpLoggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

val httpClient : OkHttpClient = OkHttpClient.Builder().apply {
    this.addInterceptor(httpLoggingInterceptor)
    this.connectTimeout(10, TimeUnit.SECONDS)
}.build()