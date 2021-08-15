package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.database.model.DatabaseAsteroid

@Dao
interface AsteroidsDao {
    companion object {
        const val TABLE_NAME = "databaseasteroid"
        const val APPROACH_DATE_COLUMN = "closeApproachDate"
    }

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $APPROACH_DATE_COLUMN ASC")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $APPROACH_DATE_COLUMN >= :startDate ORDER BY $APPROACH_DATE_COLUMN ASC")
    fun getAsteroids(startDate: Long): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $APPROACH_DATE_COLUMN >= :startDate AND $APPROACH_DATE_COLUMN <= :endDate ORDER BY $APPROACH_DATE_COLUMN ASC")
    fun getAsteroids(startDate: Long, endDate: Long): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroid)

    @Query("DELETE FROM $TABLE_NAME WHERE $APPROACH_DATE_COLUMN < :date")
    fun deleteAllBefore(date: Long)

}