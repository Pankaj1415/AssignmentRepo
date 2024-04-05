package com.stupa.util

import android.util.Patterns
import android.view.View
import java.util.regex.Pattern

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.makeInVisible() {
    visibility = View.INVISIBLE
}

fun View.visibilityGone() {
    visibility = View.GONE
}


fun String.isValidEmail() =
     Patterns.EMAIL_ADDRESS.matcher(this).matches()

val EMAIL_ADDRESS_PATTERN = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

