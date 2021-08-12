package com.udacity.asteroidradar

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.udacity.asteroidradar.api.NasaApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

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

            System.out.println(feed.toString())
        }
    }

}