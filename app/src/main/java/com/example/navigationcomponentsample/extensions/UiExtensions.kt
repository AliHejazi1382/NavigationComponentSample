package com.example.navigationcomponentsample.extensions

import android.content.Context

fun getImageResource(context: Context, photo: String?): Int {
    var photoAl = photo

    if (photoAl?.contains(".") == true) {
        photoAl = photoAl.substring(0, photoAl.lastIndexOf("."))
    }

    return context.resources.getIdentifier(photoAl, "drawable", context.packageName)
}