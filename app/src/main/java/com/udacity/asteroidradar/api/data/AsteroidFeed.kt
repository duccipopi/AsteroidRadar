package com.udacity.asteroidradar.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class AsteroidFeed(
    val links: LinksWrapper,
    @Json(name = "near_earth_objects") @JsonClass(generateAdapter = true)
    val nearEarthObjects: Map<String, List<NearEarthObject>>
)
