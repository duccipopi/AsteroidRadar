package com.udacity.asteroidradar

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.udacity.asteroidradar.database.AsteroidsDao
import com.udacity.asteroidradar.database.AsteroidsRadarDatabase
import com.udacity.asteroidradar.database.model.DatabaseAsteroid
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class DatabaseTest {

    lateinit var context: Context
    lateinit var db: AsteroidsRadarDatabase
    lateinit var asteroidDao: AsteroidsDao

    companion object {
        const val DAY = 24 * 60 * 60 * 1000
    }

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, AsteroidsRadarDatabase::class.java)
            .allowMainThreadQueries().build()
        asteroidDao = db.asteroidsDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGet() {
        val asteroid = DatabaseAsteroid(
            0xCAFE, "Aerolito QU3R0 0xCAFE", System.currentTimeMillis(),
            0.840205, 9000.1, 300000.01, 206265.1,
            true
        )

        asteroidDao.insertAll(asteroid)

        val query: List<DatabaseAsteroid>? = null

        Assert.assertNotNull(
            "Expecting 1 asteroid, instead got null",
            query
        )

        Assert.assertTrue(
            "Expecting $asteroid, instead got ${query?.get(0)}",
            query?.first() == asteroid
        )
    }

    @Test
    fun insertManyAndGetFiltered() {
        val today = Calendar.getInstance()
        with(today) {
            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val asteroids = arrayOf(
            DatabaseAsteroid(
                0xCAFE, "Aerolito QU3R0 0xCAFE", today.timeInMillis - DAY,
                0.840205, 9000.1, 300000.01, 206265.1,
                true
            ),
            DatabaseAsteroid(
                0xCACA0, "Cometa N35TL3", today.timeInMillis,
                0.4201025, 42.51, 0.343, 3.1415,
                false
            ),
            DatabaseAsteroid(
                0xC0CA, "Meteoro de Pégaso", today.timeInMillis + DAY,
                -0.99, 1.618, 11.11, 206265.1,
                false
            )
        )

        asteroidDao.insertAll(*asteroids)

        // Yesterday until Tomorrow
        var startDate = today.timeInMillis - DAY
        val expects = mutableListOf(*asteroids)

        do {
            val query = asteroidDao.getAsteroids(startDate).value

            Assert.assertTrue(
                "Expecting ${asteroids.size}, instead got ${query?.size}",
                (query != null) && (query.size == asteroids.size)
            )

            for ((expected, entity) in expects.zip(query!!)) {
                Assert.assertEquals(
                    "Expected $expected, instead got $entity",
                    expected,
                    entity
                )
            }

            startDate += DAY
            expects.removeAt(0)

        } while (expects.isNotEmpty())


    }


}