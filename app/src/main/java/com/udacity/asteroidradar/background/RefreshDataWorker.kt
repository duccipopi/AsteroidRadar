package com.udacity.asteroidradar.background

import android.content.Context
import androidx.work.*
import com.udacity.asteroidradar.repository.AsteroidRepository
import retrofit2.HttpException
import java.lang.Exception

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val UNIQUE_NAME = "RefreshDataWorker"
        val WORK_POLICY = ExistingPeriodicWorkPolicy.KEEP
        val CONSTRAINTS = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .setRequiresDeviceIdle(true)
            .build()
    }

    override suspend fun doWork(): Result {
        val repository = AsteroidRepository(applicationContext)
        return try {
            repository.refresh()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}