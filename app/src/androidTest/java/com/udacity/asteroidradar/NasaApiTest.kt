package com.udacity.asteroidradar

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.repository.model.asDateString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class NasaApiTest {
    lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun getFeed() {

        runBlocking {
            val feed = NasaApi.asteroidsService.getFeed("2021-08-12")

            Assert.assertTrue(feed.nearEarthObjects.isNotEmpty())
        }
    }

    @Test
    fun getFeedInIoContext() {
        runBlocking {
            withContext(Dispatchers.IO) {
                val feed = NasaApi.asteroidsService.getFeed(Calendar.getInstance().timeInMillis.asDateString())

                Assert.assertTrue(feed.nearEarthObjects.isNotEmpty())
            }
        }
    }

    @Test
    fun getPictureOfDay() {

        runBlocking {
            val pod = NasaApi.pictureOfDayService.get()

            Assert.assertTrue(pod.url.isNotEmpty())
        }
    }

}