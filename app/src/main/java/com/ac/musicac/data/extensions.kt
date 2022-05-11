package com.ac.musicac.data

import android.util.Base64
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import okio.IOException
import retrofit2.HttpException
import com.ac.musicac.domain.Error

fun String.encodeBase64(): String =
    Base64.encodeToString(this.toByteArray(charset("UTF-8")), Base64.NO_WRAP)

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}
