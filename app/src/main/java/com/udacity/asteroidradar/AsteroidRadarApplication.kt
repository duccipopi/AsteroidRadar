package com.udacity.asteroidradar

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.udacity.asteroidradar.background.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.TimeUnit

class AsteroidRadarApplication : Application() {

    val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        setupWorkers();
    }

    private fun setupWorkers() {
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.UNIQUE_NAME,
            RefreshDataWorker.WORK_POLICY,
            PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                .setConstraints(RefreshDataWorker.CONSTRAINTS)
                .build()
        )
    }
}