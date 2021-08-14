package com.udacity.asteroidradar

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.database.model.DatabaseAsteroid

fun waitForData(waitQuery: LiveData<*>): Any? {
    var waiting = true
    while (waiting) {
        waitQuery.observeForever { it ->
            it?.let {
                waiting = false
            }
        }
    }

    return waitQuery.value
}