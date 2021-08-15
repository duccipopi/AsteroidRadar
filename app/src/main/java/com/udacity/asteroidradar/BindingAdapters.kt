package com.udacity.asteroidradar

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.domain.MEDIA_TYPE
import com.udacity.asteroidradar.domain.PictureOfDay

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("pictureOfDay")
fun bindImageViewToPictureOfDay(imgView: ImageView, picture: PictureOfDay?) {
    picture?.let {
        if (picture.mediaType == MEDIA_TYPE.IMAGE) {
            Picasso.with(imgView.context).load(Uri.parse(picture.url))
                .placeholder(R.drawable.placeholder_picture_of_day).into(imgView)

            imgView.contentDescription = picture.title
        }
    }
}

@BindingAdapter("hazardousStatus")
fun bindImageViewToHazardous(imgView: ImageView, hazardous: Boolean) {
    imgView.setImageResource(
        if (hazardous) R.drawable.ic_status_potentially_hazardous
        else R.drawable.ic_status_normal
    )
}

