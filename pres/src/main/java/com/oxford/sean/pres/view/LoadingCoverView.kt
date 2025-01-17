package com.oxford.sean.pres.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.oxford.sean.pres.R

class LoadingCoverView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        inflate(context, R.layout.view_loading_cover, this)
        val loadingSpinner = findViewById<ProgressBar>(R.id.ProgressBar_loading_cover_view)
    }

}