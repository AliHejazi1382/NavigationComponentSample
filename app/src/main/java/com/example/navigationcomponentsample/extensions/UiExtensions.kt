package com.example.navigationcomponentsample.extensions

import android.content.Context
import android.content.res.Resources
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.model.Flowers
import com.example.navigationcomponentsample.parser.FlowerJsonParser
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

fun getImageResource(context: Context, photo: String?): Int {
    var photoAl = photo

    if (photoAl?.contains(".") == true) {
        photoAl = photoAl.substring(0, photoAl.lastIndexOf("."))
    }

    return context.resources.getIdentifier(photoAl, "drawable", context.packageName)
}

fun getFlowerList(context: Context): List<Flowers> {
    return FlowerJsonParser().parseJson(
        context.resources.openRawResource(
            R.raw.flowers_json
        )
    )
}

fun responsiveItem(imgPhoto: ShapeableImageView) {
    imgPhoto.layoutParams.width = Resources.getSystem().displayMetrics.widthPixels / 2
    imgPhoto.layoutParams.height = Resources.getSystem().displayMetrics.widthPixels / 2
}