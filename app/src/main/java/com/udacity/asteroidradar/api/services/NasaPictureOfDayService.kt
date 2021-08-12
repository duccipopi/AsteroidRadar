package com.udacity.asteroidradar.api.services

import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.NasaApi.API_KEY
import com.udacity.asteroidradar.domain.PictureOfDay
import retrofit2.http.GET

interface NasaPictureOfDayService {
    companion object {
        const val BASE_URL = NasaApi.BASE_URL
        const val END_POINT = "planetary/apod"
    }

    @GET("$END_POINT?$API_KEY")
    suspend fun get(): PictureOfDay
}