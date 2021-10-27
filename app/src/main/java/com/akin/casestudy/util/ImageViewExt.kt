package com.akin.casestudy.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadInt(imgUrl:Int) {
    Glide.with(context)
        .load(imgUrl)
        .circleCrop()
        .into(this)

}
fun ImageView.loadString(imgUrl:String) {
    Glide.with(context)
        .load(imgUrl)
        .circleCrop()
        .into(this)

}