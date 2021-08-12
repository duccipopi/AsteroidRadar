package com.udacity.asteroidradar.api.data

import com.squareup.moshi.Json

data class ApproachData(
    @Json(name = "epoch_date_close_approach")
    val date: Long,
    @Json(name = "relative_velocity")
    val velocity: VelocityWrapper,
    @Json(name = "miss_distance")
    val missDistance: DistanceWrapper,
    @Json(name = "orbiting_body")
    val orbiting: String
)
