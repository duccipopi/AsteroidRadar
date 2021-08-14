package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.api.services.NasaAsteroidsService
import com.udacity.asteroidradar.api.services.NasaPictureOfDayService
import com.udacity.asteroidradar.api.support.httpClient
import com.udacity.asteroidradar.api.support.isDebugEnabled
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NasaApi {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"

    const val API_KEY = "api_key=QLSw0wJoCkH7InXrQEM8OuQxpo39prWTR0HibvNb"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .apply { if (isDebugEnabled) client(httpClient) }
        .build()

    val asteroidsService: NasaAsteroidsService =
        retrofit.create(NasaAsteroidsService::class.java)

    val pictureOfDayService: NasaPictureOfDayService =
        retrofit.create(NasaPictureOfDayService::class.java)

}