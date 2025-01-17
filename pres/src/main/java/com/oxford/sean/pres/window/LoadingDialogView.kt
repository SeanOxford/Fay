package com.oxford.sean.pres.window

import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.oxford.sean.pres.R
import com.oxford.sean.pres.clipRoundCorners
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingDialogView(context: Context, private val onClosed: (View) -> Unit) : FrameLayout(context) {

    private val dimBg: View
    private val contentContainer: ConstraintLayout

    private var ellipsisJob: Job = Job()

    init {
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        elevation = 100f
        inflate(context, R.layout.dialog_loading, this)
        dimBg = findViewById(R.id.View_loading_dialog_dim_background)
        contentContainer = findViewById(R.id.ConstraintLayout_loading_dialog_content)
        contentContainer.clipRoundCorners()
        val ellipsisText = findViewById<TextView>(R.id.TextView_loading_dialog_ellipsis)
        ellipsisJob = CoroutineScope(Main).launch {
            val ellipsisStates = listOf("", ".", "..", "...")
            while (true) {
                for (ellipsis in ellipsisStates) {
                    delay(500)
                    ellipsisText.text = ellipsis
                }
            }
        }
        show()
    }

    private fun show() {
        ObjectAnimator.ofFloat(dimBg, ALPHA, 0f, .6f).start()
        ObjectAnimator.ofFloat(contentContainer, ALPHA, 0f, 1f).start()
    }

    fun dismiss() {
        isClickable = false
        dimBg.isClickable = false
        contentContainer.isClickable = false
        val fadeOutContent = ObjectAnimator.ofFloat(contentContainer, ALPHA, 1f, 0f)
        val fadeOutBackground = ObjectAnimator.ofFloat(dimBg, ALPHA, dimBg.alpha, 0f)
        fadeOutBackground.doOnEnd { onClosed.invoke(this) }
        fadeOutContent.start()
        fadeOutBackground.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        ellipsisJob.cancel()
    }

}
