package com.ac.musicac.ui.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("url", "circleCrop", requireAll = false)
fun ImageView.bindUrl(url: String?, crop: Boolean?) {
    if (url != null) {
        if (crop != null) loadUrlWithCircleCrop(url)
        else loadUrl(url)
    }
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) {
    visibility = if (visible == true) View.VISIBLE else View.GONE
}
