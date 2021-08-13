package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.database.model.DatabaseAsteroid

@Database(entities = [DatabaseAsteroid::class], version = 1, exportSchema = false)
abstract class AsteroidsRadarDatabase : RoomDatabase() {

    abstract val asteroidsDao: AsteroidsDao

    companion object {
        const val DB_NAME = "asteroids_radar_database"

        @Volatile
        private var INSTANCE: AsteroidsRadarDatabase? = null

        fun getInstance(context: Context): AsteroidsRadarDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidsRadarDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return INSTANCE as AsteroidsRadarDatabase;
            }
        }
    }
}