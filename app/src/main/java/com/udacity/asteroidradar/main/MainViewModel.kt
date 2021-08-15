package com.udacity.asteroidradar.main

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.databinding.ListItemAsteroidBinding
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

enum class Status { LOADING, DONE, ERROR }

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Picture of day from NasaApi or Placeholder
    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    // List of Asteroids form repository
    lateinit var asteroids: LiveData<List<Asteroid>>

    // Monitor for repository status
    private val _repoStatus = MutableLiveData<Status>()
    val repoStatus: LiveData<Status>
        get() = _repoStatus

    private val repository: AsteroidRepository by lazy {
        AsteroidRepository(application.applicationContext)
    }

    init {
        loadPictureOfDay()
        loadAsteroids()
    }

    private fun loadPictureOfDay() {
        viewModelScope.launch {
            try {
                _pictureOfDay.value = NasaApi.pictureOfDayService.get()
            } catch (e: Exception) {
                _pictureOfDay.value = null
            }
        }
    }

    private fun loadAsteroids() {
        asteroids = repository.asteroids
        _repoStatus.value = Status.LOADING
        viewModelScope.launch {
            repository.refresh()
            _repoStatus.value = Status.DONE
        }
    }


    class AsteroidsAdapter() :
        ListAdapter<Asteroid, AsteroidViewHolder>(AsteroidDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
            return AsteroidViewHolder.from(parent)
        }

        override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

    }

    class AsteroidViewHolder(private val binding: ListItemAsteroidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): AsteroidViewHolder {
                return AsteroidViewHolder(
                    ListItemAsteroidBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(item: Asteroid) {
            binding.asteroid = item
            binding.executePendingBindings()
        }

    }

    class AsteroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

    }


}