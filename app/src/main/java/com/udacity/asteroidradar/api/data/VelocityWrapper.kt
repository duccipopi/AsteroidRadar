package com.udacity.asteroidradar.api.data

import com.squareup.moshi.Json

data class VelocityWrapper(
    @Json(name = "kilometers_per_second")
    val kilometersPerSecond: String,
    @Json(name = "miles_per_hour")
    val milesPerHour: String
)
