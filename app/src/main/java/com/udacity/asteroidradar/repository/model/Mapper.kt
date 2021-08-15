package com.udacity.asteroidradar.repository.model

import com.udacity.asteroidradar.api.model.AsteroidFeed
import com.udacity.asteroidradar.api.model.NearEarthObject
import com.udacity.asteroidradar.database.model.DatabaseAsteroid
import com.udacity.asteroidradar.domain.Asteroid
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun Long.asDateString(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this)
}

fun List<DatabaseAsteroid>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate.asDateString(),
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

fun AsteroidFeed.asDatabaseModel(): Array<DatabaseAsteroid> {
    val dbAsteroids = ArrayList<DatabaseAsteroid>()

    for (item in nearEarthObjects.values) {
        for (neo in item) {
            dbAsteroids.add(neo.asDatabaseModel())
        }
    }

    return dbAsteroids.toTypedArray()
}

fun NearEarthObject.asDatabaseModel(): DatabaseAsteroid {
    return DatabaseAsteroid(
        id = this.id.toLong(),
        codename = this.name,
        closeApproachDate = this.approachData.map { it.date }.average().toLong(),
        absoluteMagnitude = this.magnitude,
        estimatedDiameter = (this.diameter.kilometers.max + this.diameter.kilometers.min) / 2,
        relativeVelocity = this.approachData.mapNotNull { it.velocity.kilometersPerSecond.toDoubleOrNull() }
            .average(),
        distanceFromEarth = this.approachData.mapNotNull { it.missDistance.astronomical.toDoubleOrNull() }
            .average(),
        isPotentiallyHazardous = this.isHazardous
    )
}

fun Calendar.asZeroHour(): Calendar {
    with(this) {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }
    return this
}