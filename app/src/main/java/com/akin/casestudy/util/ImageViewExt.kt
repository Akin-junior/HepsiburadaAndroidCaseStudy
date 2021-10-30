package com.akin.casestudy.util

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Build
import android.view.RoundedCorner
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.akin.casestudy.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadInt(imgUrl:Int) {
    Glide.with(context)
        .load(imgUrl)
        .circleCrop()
        .into(this)

}
fun ImageView.loadString(imgUrl: String?,placeholder: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(imgUrl)
        .circleCrop()
        .into(this)

}

fun ImageView.loadStringForDetailPage(imgUrl: String?, placeholder: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(imgUrl)
        .transform(RoundedCorners(55))
        .into(this)

}
fun makePlaceHolder(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}