package com.udacity.asteroidradar.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.database.AsteroidsRadarDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.repository.model.asDatabaseModel
import com.udacity.asteroidradar.repository.model.asDateString
import com.udacity.asteroidradar.repository.model.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AsteroidRepository(context: Context) {

    private var database =
        AsteroidsRadarDatabase.getInstance(context)

    val asteroids: LiveData<List<Asteroid>> by lazy {
        Transformations.map(database.asteroidsDao.getAsteroids()) {
            it.asDomainModel()
        }
    }

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            try {
                val feed =
                    NasaApi.asteroidsService.getFeed(Calendar.getInstance().timeInMillis.asDateString())
                database.asteroidsDao.insertAll(*feed.asDatabaseModel())
            } catch (e: Exception) {
                // trying later
                Log.e(AsteroidRepository::class.simpleName, "Could not get the feed: $e")
            }
        }
    }
}