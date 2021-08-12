package com.udacity.asteroidradar.api.data

import com.squareup.moshi.Json

data class DiameterRangeWrapper(
    @Json(name = "estimated_diameter_min")
    val min: Double,
    @Json(name = "estimated_diameter_max")
    val max: Double
)
