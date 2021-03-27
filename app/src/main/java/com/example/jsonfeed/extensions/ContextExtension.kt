package com.example.jsonfeed.extensions

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.jsonfeed.R

fun Context.getColorCompat(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

fun Context.getGlideErrorColor(): Int {
    return this.getColorCompat(R.color.grey_four)
}

fun Context.getGlideErrorColorDrawable(): ColorDrawable {
    return ColorDrawable(this.getGlideErrorColor())
}

fun Context.populateWithGlide(
    imageView: ImageView,
    url: String?
) {
    Glide.with(this)
        .load(Uri.parse(url))
        .error(this.getGlideErrorColorDrawable())
        .placeholder(R.drawable.loading_animation)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(imageView)
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.showErrorToast(msg: String) {
    this.showToast(formatErrorMessage(msg))
}

fun formatErrorMessage(msg: String): String {
    return "\uD83D\uDE28 Wooops $msg"
}
