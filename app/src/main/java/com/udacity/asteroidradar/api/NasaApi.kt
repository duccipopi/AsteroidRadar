package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.api.data.NearEarthObject
import com.udacity.asteroidradar.api.support.PairJsonAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NasaApi {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"

    const val API_KEY = "api_key=QLSw0wJoCkH7InXrQEM8OuQxpo39prWTR0HibvNb"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(PairJsonAdapter())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val asteroidsService: NasaAsteroidsService by lazy {
        retrofit.create(NasaAsteroidsService::class.java)
    }

    val pictureOfDayService: NasaPictureOfDayService by lazy {
        retrofit.create(NasaPictureOfDayService::class.java)
    }

}