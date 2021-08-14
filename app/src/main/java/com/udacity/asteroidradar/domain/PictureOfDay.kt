package com.udacity.asteroidradar.domain

import com.squareup.moshi.Json

object MEDIA_TYPE {
    const val IMAGE = "image"
}

data class PictureOfDay(@Json(name = "media_type") val mediaType: String, val title: String,
                        val url: String)