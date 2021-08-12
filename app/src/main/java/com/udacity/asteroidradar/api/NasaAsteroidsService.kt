package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.api.NasaApi.API_KEY
import com.udacity.asteroidradar.api.data.AsteroidFeed
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