package com.udacity.asteroidradar.api.model

import com.squareup.moshi.Json

data class AsteroidFeed(
    val links: LinksWrapper,
    @Json(name = "near_earth_objects")
    val nearEarthObjects: Map<String, List<NearEarthObject>>
)

data class LinksWrapper(val next: String, val prev: String, val self: String)

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

data class DiameterWrapper(
    val kilometers: DiameterRangeWrapper,
    val miles: DiameterRangeWrapper,
    val feet: DiameterRangeWrapper
)

data class DiameterRangeWrapper(
    @Json(name = "estimated_diameter_min")
    val min: Double,
    @Json(name = "estimated_diameter_max")
    val max: Double
)

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

data class VelocityWrapper(
    @Json(name = "kilometers_per_second")
    val kilometersPerSecond: String,
    @Json(name = "miles_per_hour")
    val milesPerHour: String
)

data class DistanceWrapper(
    val astronomical: String,
    val lunar: String,
    val kilometers: String,
    val miles: String
)





