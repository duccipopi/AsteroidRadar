package com.udacity.asteroidradar.api.data

import com.squareup.moshi.Json

data class NearEarthObject(
    val id: String,
    val name: String,
    @Json(name = "absolute_magnitude_h")
    val magnitude: Double,
    @Json(name = "estimated_diameter")
    val diameter: DiameterWrapper,
    @Json(name = "is_potentially_hazardous_asteroid")
    val isHazardous: Boolean,
    @Json(name = "close_approach_data")
    val approachData: List<ApproachData>
)
