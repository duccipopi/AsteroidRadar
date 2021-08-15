package com.udacity.asteroidradar.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.database.AsteroidsRadarDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.repository.model.asDatabaseModel
import com.udacity.asteroidradar.repository.model.asDateString
import com.udacity.asteroidradar.repository.model.asDomainModel
import com.udacity.asteroidradar.repository.model.asZeroHour
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AsteroidRepository(context: Context) {

    private var database =
        AsteroidsRadarDatabase.getInstance(context)

    var asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidsDao.getAsteroids()) {
            it.asDomainModel()
        }

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val feed =
                NasaApi.asteroidsService.getFeed(Calendar.getInstance().timeInMillis.asDateString())
            database.asteroidsDao.insertAll(*feed.asDatabaseModel())
        }
    }

    fun filter(days: Int?): LiveData<List<Asteroid>> {
        val dbAsteroids =
            if (days == null) {
                database.asteroidsDao.getAsteroids()
            } else {
                val endDate = Calendar.getInstance().asZeroHour()
                endDate.add(Calendar.DAY_OF_YEAR, days + 1)
                database.asteroidsDao.getAsteroids(
                    Calendar.getInstance().asZeroHour().timeInMillis,
                    endDate.timeInMillis
                );
            }

        return Transformations.map(dbAsteroids) {
            it.asDomainModel()
        }
    }
}