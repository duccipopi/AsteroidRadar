package com.udacity.asteroidradar.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseAsteroid(
    @PrimaryKey
    val id: Long,
    val codename: String, val closeApproachDate: Long,
    val absoluteMagnitude: Double, val estimatedDiameter: Double,
    val relativeVelocity: Double, val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)