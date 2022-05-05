package com.ac.musicac.data

import android.util.Base64

fun String.encodeBase64(): String {
    return Base64.encodeToString(this.toByteArray(charset("UTF-8")), Base64.DEFAULT)
}