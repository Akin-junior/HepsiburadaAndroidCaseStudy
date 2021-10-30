package com.akin.casestudy.components

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import com.akin.casestudy.R


class CustomTitleTextView constructor(context: Context, attributeSet: AttributeSet?) :
    AppCompatTextView(context, attributeSet) {
    init {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.title_h1_size))
        setTextColor(ContextCompat.getColor(context, R.color.subTextColor))

        //setTypeface(Typeface.createFromAsset(context.assets,"assets/poppinsblack.ttf"))

    }




}