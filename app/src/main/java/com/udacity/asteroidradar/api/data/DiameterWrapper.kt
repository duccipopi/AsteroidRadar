package com.udacity.asteroidradar.api.data

data class DiameterWrapper(
    val kilometers: DiameterRangeWrapper,
    val miles: DiameterRangeWrapper,
    val feet: DiameterRangeWrapper
)
