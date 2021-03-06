package com.udacity.asteroidradar.api.services

import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.NasaApi.API_KEY
import com.udacity.asteroidradar.api.model.AsteroidFeed
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAsteroidsService {
    companion object {
        const val URL = NasaApi.BASE_URL
        const val END_POINT = "neo/rest/v1/feed"

    }

    @GET("$END_POINT?$API_KEY")
    suspend fun getFeed(@Query("start_date") data: String): AsteroidFeed
}