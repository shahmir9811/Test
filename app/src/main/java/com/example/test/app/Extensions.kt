package com.example.test.app

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadByUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .transition(withCrossFade())
        .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
        .into(this)
}

fun ImageView.loadCircleByUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .transition(withCrossFade())
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(this)
}

fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}