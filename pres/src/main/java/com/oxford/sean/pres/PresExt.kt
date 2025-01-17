package com.oxford.sean.pres

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources.getSystem
import android.graphics.Outline
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewOutlineProvider
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowInsetsCompat

val Int.dpToPx: Int get() = (this * getSystem().displayMetrics.density).toInt()
val Int.dpToPxF: Float get() = (this * getSystem().displayMetrics.density)

fun View.show(shouldShow: Boolean = true) {
    this.visibility = if (shouldShow) VISIBLE else GONE
}

fun View.hide() {
    this.visibility = GONE
}

fun Context.getColorByRes(color: Int): Int = ContextCompat.getColor(this, color)

fun View.setRoundCornerBackground(
    backgroundColorRes: Int,
    borderColorRes: Int,
    cornerRadius: Float = 4.dpToPxF,
    borderWidth: Int = 1.dpToPx
) {
    val shape = GradientDrawable()
    shape.shape = GradientDrawable.RECTANGLE
    shape.cornerRadii = floatArrayOf(
        cornerRadius,
        cornerRadius,
        cornerRadius,
        cornerRadius,
        cornerRadius,
        cornerRadius,
        cornerRadius,
        cornerRadius
    )
    shape.setColor(context.getColorByRes(backgroundColorRes))
    shape.setStroke(borderWidth, context.getColorByRes(borderColorRes))
    background = shape
}

fun View.clipRoundCorners(cornerRadius: Float = 8.dpToPxF) {
    val viewOutlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(0, 0, view.width, view.height, cornerRadius)
        }
    }
    outlineProvider = viewOutlineProvider
    clipToOutline = true
}



fun View.setTouchRipple(backgroundColorRes: Int, rippleColorRes: Int) {
    val backgroundDrawable = ColorDrawable(this.context.getColorByRes(backgroundColorRes))
    val rippleColorWithAlpha = ColorUtils.setAlphaComponent(this.context.getColorByRes(rippleColorRes), 120)
    this.background = RippleDrawable(ColorStateList.valueOf(rippleColorWithAlpha), backgroundDrawable, null)
}

fun View.fadeOut(duration: Long = 200, startDelay: Long = 0, onEnd: () -> Unit = {}) {
    val animator = ObjectAnimator.ofFloat(this, View.ALPHA, alpha, 0f)
    animator.startDelay = startDelay
    animator.duration = duration
    animator.doOnEnd {
        visibility = GONE
        alpha = 0f
        onEnd.invoke()
    }
    animator.start()
}

val View.isKeyboardVisible: Boolean
    get() = WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets).isVisible(WindowInsetsCompat.Type.ime())

fun Context.hideKeyboard(view: View){
    val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun EditText.doOnDoneClick(action: () -> Unit){
   this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            action.invoke()
            context.hideKeyboard(this)
            true
        } else {
            false
        }
    }
}