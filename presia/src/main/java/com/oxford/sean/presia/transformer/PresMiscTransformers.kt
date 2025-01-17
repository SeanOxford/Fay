package com.oxford.sean.presia.transformer

import android.widget.Toast
import com.oxford.sean.domain.entity.ToastDurationEntityType

fun ToastDurationEntityType.toPres() = when (this) {
    ToastDurationEntityType.SHORT -> Toast.LENGTH_SHORT
    ToastDurationEntityType.LONG -> Toast.LENGTH_LONG
}